package ktr_pkg;
// import java.lang.*;
// import java.util.*;
//import java.time.*;
public class MyDate {
	private int year;
	private int month;
	private int day;
	String[] MONTHS = {
			"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	String[] DAYS = {
			"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
	};
	int[] DAYS_IN_MONTH = {
			31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
	};
	int[] LEAP_DAYS_IN_MONTH = {
			31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
	};
	public static boolean isLeapYear (int year)
	{
		boolean isleapYear = false;
		if (year % 4 == 0)
		{
			if (year % 100 == 0)
			{
				if (year % 400 == 0)
					isleapYear = true;
				else 
					isleapYear = false;
			}
			else
				isleapYear = true;
		}
		else
		{
			isleapYear = false;
		}
		return isleapYear;
	}
	public boolean isValidDate(int year, int month, int day)
	{
		if (year < 1 || year > 9999 ||
	            month < 1 || month > 12 ||
	            day < 1 || day > 31)
	        {
	            return false;
	        }
	 
	        if (isLeapYear(year))
	        {
	            if (day > LEAP_DAYS_IN_MONTH[month-1])
	            {
	                return false;
	            }
	        }
	        else
	        {
	            if (day > DAYS_IN_MONTH[month-1])
	            {
	                return false;
	            }
	        }
	        return true;
	}
	public int getDayOfWeek(int year, int month, int day)
	{
		int dayOfweek;
		dayOfweek = ((day + 2*month + 3*(month + 1)) % 5 + year + (year / 4)) % 7;
		return dayOfweek;
	}
	public MyDate(int year, int month, int day)
	{
		this.year = year;
		this.month = month;
		this.day = day;
	}
	public void setDate (int year, int month, int day)
	{
		this.year = year;
		this.month = month;
		this.day = day;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s %02d %s %04d", DAYS[getDayOfWeek(year, month, day)], day, MONTHS[month], year);
	}
	public MyDate previousDay() {
        int prevDay = this.day - 1;
        int prevMonth = this.month;
        int prevYear = this.year;

        if (prevDay == 0) {
            prevMonth--;
            if (prevMonth == 0) {
                prevYear--;
                prevMonth = 12;
            }
            prevDay = getDaysInMonth(prevMonth, prevYear);
        }

        return new MyDate(prevDay, prevMonth, prevYear);
    }

    public MyDate nextDay() {
        int nextDay = this.day + 1;
        int nextMonth = this.month;
        int nextYear = this.year;

        int daysInMonth = getDaysInMonth(nextMonth, nextYear);

        if (nextDay > daysInMonth) {
            nextMonth++;
            nextDay = 1;

            if (nextMonth > 12) {
                nextYear++;
                nextMonth = 1;
            }
        }

        return new MyDate(nextDay, nextMonth, nextYear);
    }

    public MyDate previousMonth() {
        int prevDay = this.day;
        int prevMonth = this.month - 1;
        int prevYear = this.year;

        if (prevMonth == 0) {
            prevYear--;
            prevMonth = 12;
        }

        int daysInMonth = getDaysInMonth(prevMonth, prevYear);

        if (prevDay > daysInMonth) {
            prevDay = daysInMonth;
        }

        return new MyDate(prevDay, prevMonth, prevYear);
    }

    public MyDate nextMonth() {
        int nextDay = this.day;
        int nextMonth = this.month + 1;
        int nextYear = this.year;

        if (nextMonth > 12) {
            nextYear++;
            nextMonth = 1;
        }

        int daysInMonth = getDaysInMonth(nextMonth, nextYear);

        if (nextDay > daysInMonth) {
            nextDay = daysInMonth;
        }

        return new MyDate(nextDay, nextMonth, nextYear);
    }

    public MyDate previousYear() {
        return new MyDate(this.day, this.month, this.year - 1);
    }

    public MyDate nextYear() {
        return new MyDate(this.day, this.month, this.year + 1);
    }

    private int getDaysInMonth(int month, int year) {
        int daysInMonth;

        switch (month) {
            case 2:
                daysInMonth = (year % 4 == 0) ? 29 : 28;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                daysInMonth = 30;
                break;
            default:
                daysInMonth = 31;
                break;
        }

        return daysInMonth;
    }
}
