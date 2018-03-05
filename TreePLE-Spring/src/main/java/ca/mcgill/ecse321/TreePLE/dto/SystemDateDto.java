package ca.mcgill.ecse321.TreePLE.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SystemDateDto {


		private String name;
		private Date date;
		private List<TreeDto> trees;
		
		
		public SystemDateDto() {
			
		}
		
		public SystemDateDto(Date date) {
			this.date=date;
		}
		
		
		
		public Date getDate() {
			return date;
		}
		
		
}
