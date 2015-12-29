package com.cricmania.models;

import java.util.Date;
import java.util.List;

public class Umpire extends Modifiable {
	private String firstName;
	private String lastName;
	private String middleName;
	private Date dob;
	private String contactNumber;
	private String email;
	private Integer matchCount;
	private List<String> matches;
}
