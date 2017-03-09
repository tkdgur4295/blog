package spring.sts.blog;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.model.address.AddressDAO;
import spring.model.address.AddressDTO;
import spring.utility.blog.Utility;

@Controller
public class AddressController {
	@Autowired
	private AddressDAO dao;
	
	@RequestMapping("/address/delete")
	public String delete(int no,Model model,String col, String word, String nowPage){
		
		if(dao.delete(no)){
			model.addAttribute("col", col);
			model.addAttribute("word", word);
			model.addAttribute("nowPage", nowPage);
			return "redirect:./list";
		}else{
			return "error";
		}
		
	}

	@RequestMapping(value = "/address/update", method = RequestMethod.POST)
	public String update(Model model, int no, AddressDTO dto, String col, String word, String nowPage) {

		if (dao.update(dto)) {

			model.addAttribute("col", col);
			model.addAttribute("word", word);
			model.addAttribute("nowPage", nowPage);
			return "redirect:./list";
		} else {
			return "error";
		}
	}

	@RequestMapping(value = "/address/update", method = RequestMethod.GET)
	public String update(int no, Model model) {

		model.addAttribute("dto", dao.read(no));

		return "/address/update";
	}

	@RequestMapping("/address/read")
	public String read(int no, Model model, String col, String word, String nowPage, AddressDTO dto) {

		model.addAttribute("dto", dao.read(no));
		model.addAttribute("col", col);
		model.addAttribute("word", word);
		model.addAttribute("nowPage", nowPage);

		return "/address/read";
	}

	@RequestMapping(value = "/address/create", method = RequestMethod.POST)
	public String create(Model model, AddressDTO dto) {

		boolean flag = dao.create(dto);
		if (flag) {

			return "redirect:./list";
		} else {
			return "error";
		}

	}

	@RequestMapping(value = "/address/create", method = RequestMethod.GET)
	public String create() {

		return "/address/create";
	}

	@RequestMapping("/address/list")
	public String list(HttpServletRequest request) {

		String col = Utility.checknull(request.getParameter("col"));
		String word = Utility.checknull(request.getParameter("word"));
		if (col.equals("total")) {
			word = "";
		}
		int nowPage = 1;
		if (request.getParameter("nowPage") != null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}

		int recordPerPage = 5;

		int total = dao.total(col, word);
		int sno = ((nowPage - 1) * recordPerPage) + 1;
		int eno = nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);

		List<AddressDTO> list = dao.list(map);
		Iterator<AddressDTO> iter = list.iterator();

		String paging = Utility.paging3(total, nowPage, recordPerPage, col, word);
		request.setAttribute("list", list);
		request.setAttribute("iter", iter);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("paging", paging);

		return "/address/list";
	}

}
