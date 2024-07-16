package telran.view;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.function.Function;
import java.util.function.Predicate;

public interface InputOutput {
	String readString(String prompt);

	void writeString(String str);

	default void writeLine(Object obj) {
		writeString(obj.toString() + "\n");
	}

	default <T> T readObject(String prompt, String errorPrompt, Function<String, T> mapper) {
		T res = null;
		boolean running = false;
		do {
			String str = readString(prompt);
			running = false;
			try {
				res = mapper.apply(str);
			} catch (RuntimeException e) {
				writeLine(errorPrompt + " " + e.getMessage());
				running = true;
			}

		} while (running);
		return res;
	}

	/**
	 * 
	 * @param prompt
	 * @param errorPrompt
	 * @return Integer number
	 */
	default Integer readInt(String prompt, String errorPrompt) {
	    Integer res = null;
	    boolean running = true;
	    while (running) {
	        String str = readString(prompt);
	        try {
	            res = Integer.parseInt(str);
	            running = false;
	        } catch (NumberFormatException e) {
	            writeLine(errorPrompt + " " + e.getMessage());
	        }
	    }
	    return res;
	}


	default Long readLong(String prompt, String errorPrompt) {
	    Long res = null;
	    boolean running = true;
	    while (running) {
	        String str = readString(prompt);
	        try {
	            res = Long.parseLong(str);
	            running = false;
	        } catch (NumberFormatException e) {
	            writeLine(errorPrompt + " " + e.getMessage());
	        }
	    }
	    return res;
	}


	default Double readDouble(String prompt, String errorPrompt) {
	    Double res = null;
	    boolean running = true;
	    while (running) {
	        String str = readString(prompt);
	        try {
	            res = Double.parseDouble(str);
	            running = false;
	        } catch (NumberFormatException e) {
	            writeLine(errorPrompt + " " + e.getMessage());
	        }
	    }
	    return res;
	}


	default Double readNumberRange(String prompt, String errorPrompt, double min, double max) {
	    Double res = null;
	    boolean running = true;
	    while (running) {
	        String str = readString(prompt);
	        try {
	            res = Double.parseDouble(str);
	            if (res >= min && res < max) {
	                running = false;
	            } else {
	                writeLine(errorPrompt + " Value out of range");
	            }
	        } catch (NumberFormatException e) {
	            writeLine(errorPrompt + " " + e.getMessage());
	        }
	    }
	    return res;
	}

	default String readStringPredicate(String prompt, String errorPrompt, Predicate<String> predicate) {
	    String res = null;
	    boolean running = true;
	    while (running) {
	        String str = readString(prompt);
	        if (predicate.test(str)) {
	            res = str;
	            running = false;
	        } else {
	            writeLine(errorPrompt);
	        }
	    }
	    return res;
	}

	default String readStringOptions(String prompt, String errorPrompt, HashSet<String> options) {
	    String res = null;
	    boolean running = true;
	    while (running) {
	        String str = readString(prompt);
	        if (options.contains(str)) {
	            res = str;
	            running = false;
	        } else {
	            writeLine(errorPrompt);
	        }
	    }
	    return res;
	}

	default LocalDate readIsoDate(String prompt, String errorPrompt) {
	    LocalDate res = null;
	    boolean running = true;
	    while (running) {
	        String str = readString(prompt);
	        try {
	            res = LocalDate.parse(str);
	            running = false;
	        } catch (Exception e) {
	            writeLine(errorPrompt + " " + e.getMessage());
	        }
	    }
	    return res;
	}

	default LocalDate readIsoDateRange(String prompt, String errorPrompt, LocalDate from, LocalDate to) {
	    LocalDate res = null;
	    boolean running = true;
	    while (running) {
	        String str = readString(prompt);
	        try {
	            res = LocalDate.parse(str);
	            if (res.isAfter(from) && res.isBefore(to)) {
	                running = false;
	            } else {
	                writeLine(errorPrompt + " Date out of range");
	            }
	        } catch (Exception e) {
	            writeLine(errorPrompt + " " + e.getMessage());
	        }
	    }
	    return res;
	}
}