package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private Path logDir;
    private List<LogEntity> logData = new ArrayList<>();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);

    public LogParser(Path logDir) {
        this.logDir = logDir;
        readLogs();
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return getLogEntityInPeriodStream(after, before)
                .map(LogEntity::getIp)
                .collect(toSet());
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return getLogEntityInPeriodStream(after, before)
                .filter(logEntity -> user.equals(logEntity.getUser()))
                .map(LogEntity::getIp)
                .collect(toSet());
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return getLogEntityInPeriodStream(after, before)
                .filter(logEntity -> event.equals(logEntity.getEvent()))
                .map(LogEntity::getIp)
                .collect(toSet());
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return getLogEntityInPeriodStream(after, before)
                .filter(logEntity -> status.equals(logEntity.getStatus()))
                .map(LogEntity::getIp)
                .collect(toSet());
    }

    @Override
    public Set<String> getAllUsers() {
        return logData.stream()
                .map(LogEntity::getUser)
                .collect(toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return (int) getLogEntityInPeriodStream(after, before)
                .map(LogEntity::getUser)
                .distinct()
                .count();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return (int) getLogEntityInPeriodStream(after, before)
                .filter(logEntity -> user.equals(logEntity.getUser()))
                .map(LogEntity::getEvent)
                .distinct()
                .count();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return getLogEntityInPeriodStream(after, before)
                .filter(logEntity -> ip.equals(logEntity.getIp()))
                .map(LogEntity::getUser)
                .collect(toSet());
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return getUsersByEventAndTask(after, before, Event.LOGIN, -1);
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return getUsersByEventAndTask(after, before, Event.DOWNLOAD_PLUGIN, -1);
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return getUsersByEventAndTask(after, before, Event.WRITE_MESSAGE, -1);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return getUsersByEventAndTask(after, before, Event.SOLVE_TASK, -1);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return getUsersByEventAndTask(after, before, Event.SOLVE_TASK, task);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return getUsersByEventAndTask(after, before, Event.DONE_TASK, -1);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return getUsersByEventAndTask(after, before, Event.DONE_TASK, task);
    }

    private Set<String> getUsersByEventAndTask(Date after, Date before, Event event, int task) {
        return getLogEntityInPeriodStream(after, before)
                .filter(logEntity -> logEntity.getEvent() == event && (task == -1 || logEntity.getTaskNum() == task))
                .map(LogEntity::getUser)
                .collect(toSet());
    }

    private Stream<LogEntity> getLogEntityInPeriodStream(Date after, Date before) {
        return logData.stream()
                .filter(logEntity -> logEntity.dateBetween(after, before));
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return getLogEntityInPeriodStream(after, before)
                .filter(logEntity -> event == logEntity.getEvent() && user.equals(logEntity.getUser()))
                .map(LogEntity::getDate)
                .collect(toSet());
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return getLogEntityInPeriodStream(after, before)
                .filter(logEntity -> logEntity.getStatus() == Status.FAILED)
                .map(LogEntity::getDate)
                .collect(toSet());
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return getLogEntityInPeriodStream(after, before)
                .filter(logEntity -> logEntity.getStatus() == Status.ERROR)
                .map(LogEntity::getDate)
                .collect(toSet());
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        return getLogEntityInPeriodStream(after, before)
                .filter(logEntity -> user.equals(logEntity.getUser()) && logEntity.getEvent() == Event.LOGIN)
                .map(LogEntity::getDate)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        return getLogEntityInPeriodStream(after, before)
                .filter(logEntity -> user.equals(logEntity.getUser())
                        && logEntity.getEvent() == Event.SOLVE_TASK
                        && logEntity.getTaskNum() == task)
                .map(LogEntity::getDate)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        return getLogEntityInPeriodStream(after, before)
                .filter(logEntity -> user.equals(logEntity.getUser())
                        && logEntity.getEvent() == Event.DONE_TASK
                        && logEntity.getTaskNum() == task)
                .map(LogEntity::getDate)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return getLogEntityInPeriodStream(after, before)
                .filter(logEntity -> user.equals(logEntity.getUser()) && logEntity.getEvent() == Event.WRITE_MESSAGE)
                .map(LogEntity::getDate)
                .collect(toSet());
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return getLogEntityInPeriodStream(after, before)
                .filter(logEntity -> user.equals(logEntity.getUser()) && logEntity.getEvent() == Event.DOWNLOAD_PLUGIN)
                .map(LogEntity::getDate)
                .collect(toSet());
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return (int) getLogEntityInPeriodStream(after, before)
                .map(LogEntity::getEvent)
                .distinct()
                .count();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        return getLogEntityInPeriodStream(after, before)
                .map(LogEntity::getEvent)
                .collect(toSet());
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        return getLogEntityInPeriodStream(after, before)
                .filter(logEntity -> ip.equals(logEntity.getIp()))
                .map(LogEntity::getEvent)
                .collect(toSet());
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        return getLogEntityInPeriodStream(after, before)
                .filter(logEntity -> user.equals(logEntity.getUser()))
                .map(LogEntity::getEvent)
                .collect(toSet());
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        return getLogEntityInPeriodStream(after, before)
                .filter(logEntity -> logEntity.getStatus() == Status.FAILED)
                .map(LogEntity::getEvent)
                .collect(toSet());
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        return getLogEntityInPeriodStream(after, before)
                .filter(logEntity -> logEntity.getStatus() == Status.ERROR)
                .map(LogEntity::getEvent)
                .collect(toSet());
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        return (int) getLogEntityInPeriodStream(after, before)
                .filter(logEntity -> logEntity.getEvent() == Event.SOLVE_TASK
                        && logEntity.getTaskNum() == task)
                .count();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        return (int) getLogEntityInPeriodStream(after, before)
                .filter(logEntity -> logEntity.getEvent() == Event.DONE_TASK
                        && logEntity.getTaskNum() == task)
                .count();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        return getLogEntityInPeriodStream(after, before)
                .filter(logEntity -> logEntity.getEvent() == Event.SOLVE_TASK)
                .collect(groupingBy(LogEntity::getTaskNum, summingInt(v -> 1)));
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        return getLogEntityInPeriodStream(after, before)
                .filter(logEntity -> logEntity.getEvent() == Event.DONE_TASK)
                .collect(groupingBy(LogEntity::getTaskNum, summingInt(v -> 1)));
    }

    @Override
    public Set<?> execute(String query) {
        Matcher simpleMatcher = Pattern.compile("^get (\\w+)$").matcher(query);
        Matcher filterMatcher = Pattern.compile("get (\\w+) for (\\w+) = \"([^\"]+)\"").matcher(query);
        Matcher periodMatcher = Pattern.compile("date between \"(.+)\" and \"(.+)\"").matcher(query);

        Stream<LogEntity> logEntityStream = logData.stream();
        String fieldOut = null;

        if (periodMatcher.find()) {
            try {
                Date after = dateFormat.parse(periodMatcher.group(1));
                Date before = dateFormat.parse(periodMatcher.group(2));
                logEntityStream = getLogEntityInPeriodStream(after, before);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (simpleMatcher.find()) {
            fieldOut = simpleMatcher.group(1);
        } else if (filterMatcher.find()) {
            fieldOut = filterMatcher.group(1);
            String fieldFilter = filterMatcher.group(2);
            String value = filterMatcher.group(3);

            if (fieldFilter.equals("ip"))
                logEntityStream = logEntityStream.filter(entity -> entity.getIp().equals(value));
            else if (fieldFilter.equals("user"))
                logEntityStream = logEntityStream.filter(entity -> entity.getUser().equals(value));
            else if (fieldFilter.equals("event"))
                logEntityStream = logEntityStream.filter(entity -> entity.getEvent().toString().equals(value));
            else if (fieldFilter.equals("status"))
                logEntityStream = logEntityStream.filter(entity -> entity.getStatus().toString().equals(value));
            else if (fieldFilter.equals("date"))
                logEntityStream = logEntityStream.filter(entity -> entity.getDateStr().equals(value));
        }

        return mapStreamToField(fieldOut, logEntityStream)
                .collect(toSet());
    }

    private Stream<?> mapStreamToField(String field, Stream<LogEntity> logEntityStream) {
        Stream<?> mapStream = Stream.empty();

        if (field.equals("ip")) mapStream = logEntityStream.map(LogEntity::getIp);
        else if (field.equals("user")) mapStream = logEntityStream.map(LogEntity::getUser);
        else if (field.equals("date")) mapStream = logEntityStream.map(LogEntity::getDate);
        else if (field.equals("event")) mapStream = logEntityStream.map(LogEntity::getEvent);
        else if (field.equals("status")) mapStream = logEntityStream.map(LogEntity::getStatus);

        return mapStream;
    }

    private class LogEntity {
        private String ip;
        private String user;
        private Date date;
        private String dateStr;
        private Event event;
        private int taskNum;
        private Status status;

        public LogEntity(String logStr) throws ParseException {
            String[] line_split = logStr.split("\t");

            this.ip = line_split[0];
            this.user = line_split[1];
            this.date = dateFormat.parse(line_split[2]);
            this.dateStr = line_split[2];
            this.status = Status.valueOf(line_split[4]);

            String[] eventData = line_split[3].split(" ");
            this.event = Event.valueOf(eventData[0]);
            if (eventData.length == 2)
                this.taskNum = Integer.valueOf(eventData[1]);
        }

        public String getIp() {
            return ip;
        }

        public String getUser() {
            return user;
        }

        public Date getDate() {
            return date;
        }

        public String getDateStr() {
            return dateStr;
        }

        public Event getEvent() {
            return event;
        }

        public int getTaskNum() {
            return taskNum;
        }

        public Status getStatus() {
            return status;
        }

        public boolean dateBetween(Date after, Date before) {
            return (after == null || date.after(after))
                    && (before == null || date.before(before));
        }
    }

    private void readLogs() {
        List<Path> logFiles = new ArrayList<>();
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(logDir, "*.log")) {
            paths.forEach(path -> readLogFile(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readLogFile(Path path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path.toString()))) {
            while (br.ready())
                logData.add(new LogEntity(br.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}