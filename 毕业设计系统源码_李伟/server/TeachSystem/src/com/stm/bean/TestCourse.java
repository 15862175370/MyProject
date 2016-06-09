

package com.stm.bean;

public class TestCourse {
	public TestCourse(int qusetion, String question_content, int cno,
			String answer_content_a, String answer_content_b,
			String answer_correct) {
		super();
		this.qusetion = qusetion;
		this.question_content = question_content;
		this.cno = cno;
		this.answer_content_a = answer_content_a;
		this.answer_content_b = answer_content_b;
		this.answer_correct = answer_correct;
	}
	private int qusetion;
	private String question_content;
	private int cno;
	private String answer_content_a;
	private String answer_content_b;
	private String answer_correct;
	public int getQusetion() {
		return qusetion;
	}
	public void setQusetion(int qusetion) {
		this.qusetion = qusetion;
	}
	public String getQuestion_content() {
		return question_content;
	}
	public void setQuestion_content(String question_content) {
		this.question_content = question_content;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getAnswer_content_a() {
		return answer_content_a;
	}
	public void setAnswer_content_a(String answer_content_a) {
		this.answer_content_a = answer_content_a;
	}
	public String getAnswer_content_b() {
		return answer_content_b;
	}
	public void setAnswer_content_b(String answer_content_b) {
		this.answer_content_b = answer_content_b;
	}
	public String getAnswer_correct() {
		return answer_correct;
	}
	public void setAnswer_correct(String answer_correct) {
		this.answer_correct = answer_correct;
	}
}
