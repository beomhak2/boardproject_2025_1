package com.project.page.board2.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.page.board2.model.Pager;
import com.project.page.board2.model.Post;

@Repository
public class Board2DaoOracle implements Board2Dao{

	@Autowired
	SqlSession sql;
	
	@Override
	public List<Post> list(Pager pager) {
		return sql.selectList("board2.list", pager);
	}

	@Override
	public void add(Post item) {
		sql.insert("board2.add", item);
	}

	@Override
	public Post item(int postId) {
		return sql.selectOne("board2.item", postId);
	}

	@Override
	public void updateViewCnt(int postId) {
		sql.update("board2.updateViewCnt", postId);
	}
	
	@Override
	public void update(Post item) {
		sql.update("board2.update", item);
	}

	@Override
	public void delete(int postId) {
		sql.update("board2.delete", postId);
	}

	@Override
	public int total(Pager pager) {
		return sql.selectOne("board2.total", pager);
	}

	@Override
	public int replyCount(int postId) {
		return sql.selectOne("board2.replyCount", postId);
	}

}
