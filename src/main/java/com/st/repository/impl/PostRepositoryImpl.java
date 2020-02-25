package com.st.repository.impl;

import com.st.db.tables.records.PostsRecord;
import com.st.entity.Post;
import com.st.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

import static com.st.db.tables.Posts.POSTS;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PostRepositoryImpl implements PostRepository {

    private final DSLContext dsl;

    private Long insert(Post post) {
        PostsRecord postsRecord = dsl.insertInto(POSTS, POSTS.TITLE, POSTS.DESCRIPTION, POSTS.TEXT, POSTS.CREATED_AT)
                .values(post.getTitle(), post.getDescription(), post.getText(), post.getDate())
                .returning(POSTS.ID)
                .fetchOne();
        post.setId(postsRecord.getValue(POSTS.ID));
        log.info("Insert into db: {}", post.toString());
        return postsRecord.getValue(POSTS.ID);
    }

    @Override
    public Post create(Post post) {
        return get(insert(post));
    }

    @Override
    public boolean update(Post post) {
        try {
            dsl.update(POSTS)
                    .set(POSTS.TITLE, post.getTitle())
                    .set(POSTS.TEXT, post.getText())
                    .set(POSTS.DESCRIPTION, post.getDescription())
                    .where(POSTS.ID.eq(post.getId())).execute();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Post get(Long id) {
        Post post = dsl.selectFrom(POSTS)
                .where(POSTS.ID.eq(id))
                .fetchOneInto(Post.class);
        post.setDate(dsl.select(POSTS.CREATED_AT).from(POSTS).where(POSTS.ID.eq(id)).fetchOneInto((Timestamp.class)));
        return post;
    }

    @Override
    public List<Post> getAll() {
        return dsl.selectFrom(POSTS)
                .orderBy(POSTS.ID.desc())
                .fetch(r->{
                    Post post = new Post();
                    post.setId(r.get(0,Long.class));
                    post.setTitle(r.get(1,String.class));
                    post.setDescription(r.get(2,String.class));
                    post.setText(r.get(3,String.class));
                    post.setDate(r.get(4, Timestamp.class));
                    return  post; });
    }

    @Override
    public boolean remove(Long id) {
        log.info("remove post from db");
        try {
            dsl.deleteFrom(POSTS)
                    .where(POSTS.ID.eq(id)).execute();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
    }
}
