package guestbook.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.bean.GuestbookDTO;
import guestbook.dao.GuestbookDAO;

@WebServlet("/GuestbookListServlet")
public class GuestbookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//DB
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		out.println("<html>");
		out.println("<head>");
		out.println("<style> td  {"+
				"  overflow: hidden; \r\n" + 
				"  text-overflow: ellipsis;\r\n" + 
				"  white-space: nowrap; \r\n" + 
				"  width: 100px;\r\n" + 
				"  height: 20px;}");
		out.println("body span { "+
		     "display: block; \r\n" +  // inline-block���� ���� �ʿ�
			"} </style>");
		out.println("</head>");
		out.println("<body>");
		
		
		
		//db���� arrayList��������
		GuestbookDAO guestbookDAO = GuestbookDAO.getInstance();
		ArrayList<GuestbookDTO> guestbookList = new ArrayList<>();
		guestbookList = guestbookDAO.list();
		
		//1�������� 3����
		
		//����¡ ó��
		int totalA = guestbookDAO.getTotalA();	//�� �ۼ�
		int totalP = (totalA + 2) / 3; //�� ������ ��
		
		
		out.println("<h2 align = 'center'>�۸��</h2>");
		for(GuestbookDTO dto : guestbookList) {
			out.println("<table style='table-layout: fixed;' border = 1 cellpadding = '5' cellspacing ='0' align = 'center' >");
			out.println("<tr>");
			out.println("<td width = 100>�ۼ���</td>");
			out.println("<td width = 100>"+dto.getName()+"</td>");
			out.println("<td width = 100>�ۼ���</td>");
			out.println("<td width = 100>"+dto.getLogtime()+"</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>�̸���</td>");
			out.println("<td colspan = '3'>"+dto.getEmail()+"</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>Ȩ������</td>");
			out.println("<td colspan = '3'><a href = '"+dto.getHomepage()+"'>"+dto.getHomepage()+"</a></td>");
//			out.println("<td colspan = '3' style='width:50%; text-overflow:ellipsis; overflow:hidden; white-space:nowrap;'><a href = '"+dto.getHomepage()+"'</a>"+dto.getHomepage()+"</td>");
			out.println("</tr>");
					
			out.println("<tr>");
			out.println("<td>����</td>");
			out.println("<td colspan = '3'>"+dto.getSubject()+"</td>");
			out.println("</tr>");
			/*
			out.println("<tr>");
			out.println("<td colsspan = '4'><textarea rows = '5' cols = '40' style = 'overflow-y:scroll; resize:none;' readonly>"+dto.getContent()+"</textarea></td>");
			out.println("</tr>");
			*/
			out.println("<tr>");
			out.println("<td colspan = '4'><pre>"+dto.getContent()+"</pre></td>");
			out.println("</tr>");
			
			out.println("</table>");
			out.println("<br>");
			out.println("<br>");
			out.println("<hr color = 'red' width = '500' align = 'center' >");
		}
		
		out.println("</body>");
		out.println("</html>");
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("����Ʈ");
		out.println("<head>");
		out.println("</head>");
		out.println("</html>");
	}
	
	

}
