package com.project.page.board4.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.page.board4.model.Pager;
import com.project.page.model.Board;
import com.project.page.board4.model.Post;
import com.project.page.model.Reply;

@Repository
public class Board4DaoOracle implements Board4Dao{

	@Autowired
	SqlSession sql;
	
	   /**
	    * 이 메서드는 두 개의 정수를 입력받아 합을 반환합니다.
	    *
	    * @param a 첫 번째 정수
	    * @param b 두 번째 정수
	    * @return 두 정수의 합
	    */
	@Override
	public List<Post> list(Pager pager) {
		List<Post> list = sql.selectList("board4.list", pager);
		
		return list;
	}

	@Override
	public void add(Post item) {
		sql.insert("board4.insert", item);
	}

	@Override
	public Board item(int boardId) {
		return null;
	}

	@Override
	public Post detail(String postId) {
		return sql.selectOne("board4.detail",postId);
	}

	@Override
	public void update(Post post) {
		sql.update("board4.update", post);
	}

	@Override
	public void viewCntUp(String postId) {
		sql.update("board4.viewCntUp", postId);
	}

	@Override
	public void delete(String postId) {
		sql.update("board4.delete", postId);
	}

	@Override
	public int replyInsert(Reply reply) {
		return sql.update("board4.replyInsert", reply);
	}

	@Override
	public List<Reply> getReplyList(Reply reply) {
		List<Reply> list = sql.selectList("board4.replyList", reply);
		return list;
	}

	@Override
	public int total(Pager pager) {
		return sql.selectOne("board4.total",pager);
	}

	@Override
	public void dummy(Map<String, Object> map) {
		sql.insert("board4.dummy",map);
		
	}

	@Override
	public int replyDelete(Reply reply) {
		return sql.delete("board4.replyDelete", reply);
	}
}
