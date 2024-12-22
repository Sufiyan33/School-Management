package com.sufiyan.dto;

import java.util.Date;

import com.sufiyan.enums.StudentLeaveStatus;

public class StudentLeaveDto {

	private Long id;
	private String subject;
	private String body;
	private Date date;
	private StudentLeaveStatus studentLeaveStatus;
	private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public StudentLeaveStatus getStudentLeaveStatus() {
		return studentLeaveStatus;
	}

	public void setStudentLeaveStatus(StudentLeaveStatus studentLeaveStatus) {
		this.studentLeaveStatus = studentLeaveStatus;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "StudentLeaveDto [id=" + id + ", subject=" + subject + ", body=" + body + ", date=" + date
				+ ", studentLeaveStatus=" + studentLeaveStatus + ", userId=" + userId + "]";
	}
}
