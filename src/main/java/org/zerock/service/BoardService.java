package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;

public interface BoardService {
	// 게시글 등록
	public void regist(BoardVO board) throws Exception;
	// 게시글 읽기
	public BoardVO read(Integer bno) throws Exception;
	// 게시글 수정
	public void modify(BoardVO board) throws Exception;
	// 게시글 삭제
	public void remove(Integer bno) throws Exception;
	// 게시글 목록 조회
	public List<BoardVO> listAll() throws Exception;
	// 페이징 처리
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	// 게시물 총 갯수
	public int listCountCriteria(Criteria cri) throws Exception;
	// 게시글 검색처리
	public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception;
	// 게시물 총 갯수
	public int listSearchCount(SearchCriteria cri) throws Exception;
}
