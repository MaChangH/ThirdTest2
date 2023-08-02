package com.teamproject.teamproject.board;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Service
public class BoardDAO {

	private int allBoardCount;
	
	@Autowired
	private SqlSession ss;
	
	// 전체 게시글 수 가져오는 method
	public void countAllBoard() {
		allBoardCount = ss.getMapper(BoardMapper.class).getAllBoardCount();
	}
	
	// 검색어 초기화 method
	public void searchClear(HttpServletRequest req) {
		req.getSession().setAttribute("search", null);
	}
	
	// 검색어에 해당하는 게시글 가져오는 method
	public void getBoardMsg(int page, HttpServletRequest req) {
		String search = (String) req.getSession().getAttribute("search"); // 검색어

		int boardCount = 0;
		if (search == null) { // 전체조회
			boardCount = allBoardCount; // mapper의 sql로 가서 전체 조회한 값
			search = "";
			
		} else { // 검색
			BoardSelector bSel2 = new BoardSelector(search, 0, 0);
			boardCount = ss.getMapper(BoardMapper.class).getSearchBoardCount(bSel2);
		}
		int PerPage = 10;
		int allPageCount = (int) Math.ceil(boardCount / (double) PerPage);
		req.setAttribute("allPageCount", allPageCount);
		int start = (PerPage * (page - 1)) + 1;
		int end = (page == allPageCount) ? boardCount : (start + PerPage - 1);
		BoardSelector bSel = new BoardSelector(search, start, end);
		List<Board> boards = ss.getMapper(BoardMapper.class).getAllBoard(bSel);
		req.setAttribute("boardMsg", boards);
	}
	
	// 클릭한 게시글 전체 내용 보여주는 method
	public void viewBoard(int tp_b_no, HttpServletRequest req) {
		BoardNo bn = new BoardNo(tp_b_no);
		List<Board> boards = ss.getMapper(BoardMapper.class).viewBoard(bn);
		Board board = boards.get(0);
		req.getSession().setAttribute("boardManager", board);
		req.getSession().setAttribute("boardNo", tp_b_no);
		req.setAttribute("boards", boards);
	}
	
	// 검색어 입력시 세션에 검색어 저장하는 method
	public void searchBoard(HttpServletRequest req) {
		String search = req.getParameter("search");
		req.getSession().setAttribute("search", search);
	}
	
	// 게시글 작성하는 method
	public void writeBoard(Board b, HttpServletRequest req) {
		try {
			String token = req.getParameter("token");
			String st2 = (String) req.getSession().getAttribute("st");

			if (st2 != null && token.equals(st2)) {
				req.setAttribute("r", "글쓰기실패(새로고침)");
				return;
			}
			String path = req.getSession().getServletContext().getRealPath("resources/img");

			MultipartRequest mr = new MultipartRequest(req, path, 10485760, "utf-8", new DefaultFileRenamePolicy());
			String title = mr.getParameter("tp_b_title");
			b.setTp_b_title(title);
			b.setTp_b_writer(mr.getParameter("tp_b_writer"));
			String txt = mr.getParameter("tp_b_txt");
			b.setTp_b_txt(txt);
			String tp_b_photo = mr.getFilesystemName("tp_b_photo");
			String tp_b_photo_kor = null;
			if (tp_b_photo == null) {
				if (ss.getMapper(BoardMapper.class).writeBoard(b) == 1) {
					req.setAttribute("r", "글쓰기성공");
					req.getSession().setAttribute("st", token);
					allBoardCount++;
				}else {
					req.setAttribute("r", "글쓰기실패");
				}
			}else if (tp_b_photo != null) {
				tp_b_photo_kor = URLEncoder.encode(tp_b_photo, "utf-8").replace("+", " ");
				b.setTp_b_photo(tp_b_photo_kor);
				if (ss.getMapper(BoardMapper.class).writeBoardPhoto(b) == 1) {
					req.setAttribute("r", "글쓰기성공-pt");
					req.getSession().setAttribute("st", token);
					allBoardCount++;
				}else {
					req.setAttribute("r", "글쓰기실패-pt");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "글쓰기실패-db");
			System.out.println("글쓰기실패-db");
		}
	}
	
	// 게시글 삭제하는 method
	public void deleteBoard(int tp_b_no, HttpServletRequest req) {
		try {
			BoardNo bn = new BoardNo(tp_b_no);
			if (ss.getMapper(BoardMapper.class).deleteBoard(bn) == 1) {
				req.setAttribute("r", "글삭제성공");
				allBoardCount--;
			}else {
				req.setAttribute("r", "글삭제실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "글삭제실패-DB");
		}
	}
	
	
	// 게시글 수정하는 method
	public void updateBoard(Board b, HttpServletRequest req) {
		String path = req.getSession().getServletContext().getRealPath("resources/img");
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(req, path, 10 * 1024 * 1024, "utf-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "수정실패(파일용량초과)");
			return;
		}
		Board otk = (Board) req.getSession().getAttribute("boardManager");
		String oldFile = otk.getTp_b_photo();
		String newFile = mr.getFilesystemName("tp_b_photo");
//		System.out.println(oldFile);
//		System.out.println(newFile);
		try {
			String token = req.getParameter("token");

			String st2 = (String) req.getSession().getAttribute("st");
			System.out.println(token);
			System.out.println(st2);

			if (st2 != null && token.equals(st2)) {
				req.setAttribute("r", "글쓰기실패(새로고침)");
				return;
			}
			b.setTp_b_no(Integer.parseInt(mr.getParameter("tp_b_no")));
			System.out.println(mr.getParameter("tp_b_no"));
			String title = mr.getParameter("tp_b_title");
			b.setTp_b_title(title);
			System.out.println(title);
			String txt = mr.getParameter("tp_b_txt");
			b.setTp_b_txt(txt);
			System.out.println(txt);
			if (newFile == null) { // 사진은 수정 안하는
				newFile = oldFile;
				if (ss.getMapper(BoardMapper.class).updateBoard(b) == 1) {
					req.setAttribute("r", "글수정성공");
					req.getSession().setAttribute("st", token);
				}else {
					req.setAttribute("r", "글수정실패");
				}
			} else if (newFile != null){ // 사진 수정
				newFile = URLEncoder.encode(newFile, "utf-8").replace("+", " ");
				b.setTp_b_photo(newFile);
				if (ss.getMapper(BoardMapper.class).updateBoardPhoto(b) == 1) {
					req.setAttribute("r", "글수정성공-pt");
					req.getSession().setAttribute("st", token);
					if (!oldFile.equals(newFile)) {
						oldFile = URLDecoder.decode(oldFile, "utf-8");
						new File(path + "/" + oldFile).delete();
					}
				}else {
					req.setAttribute("r", "글수정실패-pt");
					if (!oldFile.equals(newFile)) {
						newFile = URLDecoder.decode(newFile, "utf-8");
						new File(path + "/" + newFile).delete();
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "글수정실패-db");
			System.out.println("글수정실패-db");
			if (!oldFile.equals(newFile)) {
				try {
					newFile = URLDecoder.decode(newFile, "utf-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				new File(path + "/" + newFile).delete();
			}
		}
	}
	
	// 해당 게시글에 있는 댓글 불러오는 method
	public void getReply(HttpServletRequest req) {
		try {
			ReplyNo r = new ReplyNo();
			r.setTp_r_b_no((Integer) req.getSession().getAttribute("boardNo"));
			
			List<Reply> replys = ss.getMapper(BoardMapper.class).getReply(r);
			req.setAttribute("Reply", replys);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 게시글에 댓글 작성하는 method
	public void writeReply(int tp_b_no, HttpServletRequest req, Reply r) {
		try {
			r.setTp_r_writer(req.getParameter("tp_r_writer"));
			r.setTp_r_text(req.getParameter("tp_r_text").replace("<br>", "\r\n"));
			r.setTp_r_b_no(tp_b_no);
			
			String formerToken = (String) req.getSession().getAttribute("st");
			String token = req.getParameter("token2");
			
			if (!token.equals(formerToken)) {
				ss.getMapper(BoardMapper.class).writeReply(r);
				req.setAttribute("r", "댓글 작성 성공");
				req.getSession().setAttribute("st", token);
				
				System.out.println(formerToken);
				System.out.println(token);
			} 
			
		} catch (Exception e) {
			req.setAttribute("r", "댓글 작성 실패");
			e.printStackTrace();
		}
	}
	
	public void deleteReply(int tp_r_b_no, int tp_r_no, HttpServletRequest req) {
		try {
			ReplyNo rn = new ReplyNo(tp_r_b_no, tp_r_no);
			if (ss.getMapper(BoardMapper.class).deleteReply(rn) == 1) {
				req.setAttribute("r", "댓글삭제성공");
				allBoardCount--;
			}else {
				req.setAttribute("r", "댓글삭제실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("r", "댓글삭제실패-DB");
		}
	}
	
	
}
