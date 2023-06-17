package com.javarush.task.task20.task2002;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/* 
Читаем и пишем в файл: JavaRush
*/

public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File yourFile = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();

            User ivanov = new User();
            ivanov.setFirstName("Ivan");
            ivanov.setLastName("Ivanov");
            ivanov.setMale(true);
            ivanov.setCountry(User.Country.UKRAINE);
            ivanov.setBirthDate(new Date(77777777));
            javaRush.users.add(ivanov);

            User petrova = new User();
            petrova.setFirstName("Anna");
            petrova.setLastName("Petrova");
            petrova.setMale(false);
            petrova.setCountry(User.Country.RUSSIA);
            petrova.setBirthDate(new Date(8888888));
            javaRush.users.add(petrova);

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            System.out.println(javaRush.equals(loadedObject));

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter printWriter = new PrintWriter(outputStream);
            for (User user : users) {
                printWriter.printf("%s;%s;%s;%s;%s\n",
                        user.getFirstName(),
                        user.getLastName(),
                        user.getBirthDate().getTime(),
                        user.isMale(),
                        user.getCountry().getDisplayName());
            }
            printWriter.close();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                User user = new User();
                user.setFirstName(parts[0]);
                user.setLastName(parts[1]);

                Date birthDate = new Date();
                birthDate.setTime(Long.parseLong(parts[2]));
                user.setBirthDate(birthDate);

                user.setMale(Boolean.valueOf(parts[3]));

                user.setCountry(User.Country.getCountry(parts[4]));
                users.add(user);
            }
            br.close();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;
            return Objects.equals(users, javaRush.users);

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
