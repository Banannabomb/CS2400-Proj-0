
//
// Name: Lin, Alex
// Project: 3
// Due: 10/19/2023
// Course: cs-1400-02-f23
//
// Description:
// A program which takes a month and a year and outputs a calendar for that month and year
//
import java.io.*;
import java.util.Scanner;

public class MonthlyCalendarGenerator {
	static int getDayOfWeek(int day, int month, int year) {
		int a = (14 - month) / 12;
		int y = year - a;
		int m = month + 12 * a - 2;
		int d = (day + y + (y / 4) - (y / 100) + (y / 400) + (31 * m / 12)) %
				7;
		return d;
	}

	static boolean isLeapYear(int year) {
		return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
	};

	static int getNumberOfDaysInMonth(int month, int year) {
		return switch (month) {
			case 4, 6, 9, 11 -> 30;
			case 2 -> isLeapYear(year) ? 29 : 28;
			default -> 31;
		};
	}

	static String getMonthName(int month) {
		String[] monthNames = {
				"January", "February", "March", "April",
				"May", "June", "July", "August",
				"September", "October", "November", "December"
		};
		return monthNames[month - 1];
	};

	public static void main(String[] args) throws IOException {
		Scanner keyboard = new Scanner(System.in);
		int month, year, date, currentDay;
		System.out.println("Calendar by A. Lin\n");
		System.out.print("Enter month year ? ");
		month = keyboard.nextInt();
		year = keyboard.nextInt();
		int numberOfDays = getNumberOfDaysInMonth(month, year);
		String nameOfMonth = getMonthName(month);
		String fileName = month + "-" + year + ".txt";
		PrintWriter calendar = new PrintWriter(fileName);
		calendar.printf("%s %d\n\n", nameOfMonth, year);
		calendar.println("Sun Mon Tue Wed Thu Fri Sat");
		calendar.println("---------------------------------");
		date = 1;
		currentDay = 0;
		int firstDay = getDayOfWeek(date, month, year);
		while (currentDay != firstDay) {
			if (currentDay == 0)
				calendar.print(" ");
			else
				calendar.print(" ");
			currentDay++;
		}
		while (date <= numberOfDays) {
			int day = getDayOfWeek(date, month, year);
			if (day == 0)
				calendar.printf("%3d", date);
			else
				calendar.printf("%5d", date);
			if ((day + 1) % 7 == 0) {
				calendar.print("\n");
			}
			date++;
		}
		calendar.close();
		System.out.printf("\n%s generated.", fileName);
	}
}