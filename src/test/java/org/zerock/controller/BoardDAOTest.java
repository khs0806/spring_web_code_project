package org.zerock.controller;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.persistence.BoardDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class BoardDAOTest {
	private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);

	@Inject
	private BoardDAO dao;


	@Test
	public void testCreate() throws Exception {

		BoardVO board = new BoardVO();
		board.setTitle("새로운 글을 넣습니다. ");
		board.setContent("새로운 글을 넣습니다. ");
		board.setWriter("user00");
		dao.create(board);
	}

	@Test
	public void testRead() throws Exception {

		logger.info(dao.read(3).toString());
	}

	@Test
	public void testUpdate() throws Exception {

		BoardVO board = new BoardVO();
		board.setBno(1);
		board.setTitle("수정된 글입니다.");
		board.setContent("수정 테스트 ");
		dao.update(board);
	}

	@Test
	public void testDelete() throws Exception {

		dao.delete(1);
	}

	@Test
	public void testListAll() throws Exception {

		logger.info(dao.listAll().toString());

	}

	@Test
	public void testListPage() throws Exception {
		int page = 11;
		List<BoardVO> list = dao.listPage(page);
		for (BoardVO vo : list) {
			logger.info(vo.getBno() + ":" + vo.getTitle());
		}
	}

	@Test
	public void testListCriteria() throws Exception {

		Criteria cri = new Criteria();
		cri.setPage(3);					// 몇 페이지?
		cri.setPerPageNum(20);			// 페이지 당 게시물은?

		List<BoardVO> list = dao.listCriteria(cri);

		for (BoardVO boardVO : list) {
			logger.info(boardVO.getBno() + ":" + boardVO.getTitle());
		}
	}

	// UriComponentBuilde 객체로 uri 생성 테스트
	@Test
	public void testURI() throws Exception {

		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
				.path("/board/read")
				.queryParam("bno", 12)
				.queryParam("perPageNum", 20).build();

		logger.info("/board/read?bno=12&perPageNum=20");
		logger.info(uriComponents.toString());
	}
	
	@Test
	public void testURI2() throws Exception {

		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
				.path("/{module}/{page}")
				.queryParam("bno", 12)
				.queryParam("perPageNum", 20)
				.build().expand("board", "read").encode();

		logger.info("/board/read?bno=12&perPageNum=20");
		logger.info(uriComponents.toString());
	}
	
}
