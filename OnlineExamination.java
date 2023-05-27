package company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class User {
    private String username;
    private String password;
    private String name;
    private int age;

    public User(String username, String password, String name, int age) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void updateProfile(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Profile updated successfully.");
    }

    public void updatePassword(String password) {
        this.password = password;
        System.out.println("Password updated successfully.");
    }

    public void displayProfile() {
        System.out.println("Username: " + username);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

class Exam {
    private Map<Integer, String> questions;
    private Map<Integer, String> answers;
    private int totalMarks;

    public Exam() {
        questions = new HashMap<>();
        answers = new HashMap<>();
        totalMarks = 0;
        initializeQuestions();
    }

    private void initializeQuestions() {
        // Add questions and correct answers to the map
        questions.put(1, "What is the capital of France?");
        answers.put(1, "Paris");
        totalMarks += 1;

        questions.put(2, "What is the largest planet in our solar system?");
        answers.put(2, "Jupiter");
        totalMarks += 1;

        questions.put(3, "Who painted the Mona Lisa?");
        answers.put(3, "Leonardo da Vinci");
        totalMarks += 1;
    }

    public void startExam() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        int marksObtained = 0;

        System.out.println("Welcome to the online examination!");

        for (int i = 1; i <= questions.size(); i++) {
            System.out.println("\nQuestion " + i + ":");
            System.out.println(questions.get(i));
            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine();
            if (userAnswer.equalsIgnoreCase(answers.get(i))) {
                marksObtained += 1;
            }
        }

        System.out.println("\nExam completed!");
        System.out.println("Total marks: " + totalMarks);
        System.out.println("Marks obtained: " + marksObtained);
    }
}

class SessionTimer {
    private Timer timer;
    private int duration; // Exam duration in minutes

    public SessionTimer(int duration) {
        this.duration = duration;
    }

    public void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("\nTime's up! The exam will be automatically submitted.");
                timer.cancel();
            }
        }, duration * 60 * 1000);
    }

    public void cancelTimer() {
        timer.cancel();
    }
}

public class OnlineExamination {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        User user = null;
        Exam exam = null;
        SessionTimer timer = null;

        do {
            System.out.println("\nOnline Examination System");
            System.out.println("1. Login");
            System.out.println("2. Update Profile");
            System.out.println("3. Update Password");
            System.out.println("4. Start Exam");
            System.out.println("5. Close Session");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    if (user != null) {
                        System.out.println("You are already logged in as " + user.getUsername());
                        break;
                    }
                    System.out.print("Enter your username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();
                    user = loginUser(username, password);
                    if (user != null) {
                        System.out.println("Login successful! Welcome, " + user.getUsername() + ".");
                    } else {
                        System.out.println("Invalid credentials. Please try again.");
                    }
                    break;
                case 2:
                    if (user == null) {
                        System.out.println("You are not logged in.");
                        break;
                    }
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    user.updateProfile(name, age);
                    break;
                case 3:
                    if (user == null) {
                        System.out.println("You are not logged in.");
                        break;
                    }
                    System.out.print("Enter your new password: ");
                    String newPassword = scanner.nextLine();
                    user.updatePassword(newPassword);
                    break;
                case 4:
                    if (user == null) {
                        System.out.println("You are not logged in.");
                        break;
                    }
                    if (exam != null) {
                        System.out.println("An exam is already in progress.");
                        break;
                    }
                    if (timer != null) {
                        timer.cancelTimer();
                    }
                    exam = new Exam();
                    timer = new SessionTimer(10); // Exam duration in minutes
                    timer.startTimer();
                    exam.startExam();
                    exam = null;
                    break;
                case 5:
                    if (user == null) {
                        System.out.println("You are not logged in.");
                        break;
                    }
                    if (timer == null) {
                        System.out.println("No session is currently active.");
                        break;
                    }
                    timer.cancelTimer();
                    System.out.println("Session closed.");
                    break;
                case 6:
                    if (user != null) {
                        System.out.println("Logged out successfully. Goodbye, " + user.getUsername() + "!");
                    } else {
                        System.out.println("You are not logged in.");
                    }
                    user = null;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    private static User loginUser(String username, String password) {
        // Check username and password against a database or data store
        // Return the user if found, otherwise return null
        User user1 = new User("john123", "password", "John", 25);
        User user2 = new User("emma456", "123456", "Emma", 30);

        if (user1.getUsername().equals(username) && user1.getPassword().equals(password)) {
            return user1;
        } else if (user2.getUsername().equals(username) && user2.getPassword().equals(password)) {
            return user2;
        }

        return null;
    }
}

