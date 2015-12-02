package com.yash.training.tms.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import com.yash.training.tms.domain.Course;
import com.yash.training.tms.service.CourseServiceBeanLocal;

@SessionScoped
@ManagedBean
public class TrainerBean {

		private int course_id;
		private Course course;
		private List<Course> courses;
		private int subHeading_id;
		private int subheadingStatus=-1;
		@EJB CourseServiceBeanLocal courseService;
	
		public int getSubHeading_id() {
			return subHeading_id;
		}



		public void setSubHeading_id(int subHeading_id) {
			this.subHeading_id = subHeading_id;
		}





		public int getSubheadingStatus() {
			return subheadingStatus;
		}



		public void setSubheadingStatus(int subheadingStatus) {
			this.subheadingStatus = subheadingStatus;
		}



		public int getCourse_id() {
			return course_id;
		}
		public void setCourse_id(int course_id) {
			this.course_id = course_id;
		}
		public Course getCourse() {
			return course;
		}
		public void setCourse(Course course) {
			this.course = course;
		}
		public List<Course> getCourses() {
			courses=courseService.getActiveCourseList();
			return courses;
		}
		public void setCourses(List<Course> courses) {
			this.courses = courses;
		}
		
		
		public void selectCourse(ValueChangeEvent e){
			course_id=Integer.parseInt(e.getNewValue().toString());
			course=courseService.getDetailedCourse(course_id);
		}
		
		public void selectstatus(ValueChangeEvent e){
			subheadingStatus=Integer.parseInt(e.getNewValue().toString());
			}
		
		public String updateStatus(){
			
			if(subheadingStatus==0){
				courseService.updateSubHeadingStatus(subHeading_id,"Not Started");
				
			}
			if(subheadingStatus==1){
				courseService.updateSubHeadingStatus(subHeading_id,"Pending");
				
			}
			if(subheadingStatus==2)	{
				courseService.updateSubHeadingStatus(subHeading_id,"Completed");
				}
			if(subheadingStatus==-1)	
			{
				return "TrainerDashBoard.xhtml?msg=Please select status And Then update &faces-redirect=true";
			}
			course=courseService.getDetailedCourse(course_id);
			subheadingStatus=-1;
			return "TrainerDashBoard.xhtml?msg=Updated&faces-redirect=true";
		}
}
