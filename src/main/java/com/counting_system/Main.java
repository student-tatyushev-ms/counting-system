package com.counting_system;

import com.counting_system.controller.Controller;
import com.counting_system.view.MainWindow;
import com.counting_system.view.Window;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String url = "jdbc:h2:~/CountingSystem";
    private static final String user = "";
    private static final String password = "";

    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(url, user, password);
        Controller controller = new Controller(connection);
        Scanner scanner = new Scanner(System.in);
        int command;
        List<Window> windowList = new ArrayList<>();
        windowList.add(new MainWindow(scanner, controller));
        while (windowList.size() > 0) {
            Window currentWindow = windowList.get(windowList.size() - 1);

            currentWindow.printMenu();

            command = scanner.nextInt();
            if (command == 0) {
                windowList.remove(currentWindow);
            } else {
                Window newWindow = currentWindow.action(command);
                if (newWindow != null) {
                    windowList.add(newWindow);
                }
            }
        }
        connection.close();
    }

}
