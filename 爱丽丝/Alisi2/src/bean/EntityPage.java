package bean;

import java.util.List;

public class EntityPage {

	
		 private int totalPage;
		   private int currentPage;
		   private List<Entity> pageUers;
		public int getTotalPage() {
			return totalPage;
		}
		public void setTotalPage(int totalPage) {
			this.totalPage = totalPage;
		}
		public int getCurrentPage() {
			return currentPage;
		}
		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
		}
		public List<Entity> getPageUers() {
			return pageUers;
		}
		public void setPageUers(List<Entity> pageUers) {
			this.pageUers = pageUers;
		}
		public EntityPage(int totalPage, int currentPage, List<Entity> pageUers) {
			super();
			this.totalPage = totalPage;
			this.currentPage = currentPage;
			this.pageUers = pageUers;
		}
		
}
