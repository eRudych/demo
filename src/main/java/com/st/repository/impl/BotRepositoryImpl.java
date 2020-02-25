package com.st.repository.impl;

import com.st.repository.BotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static com.st.db.tables.Telegrammessages.TELEGRAMMESSAGES;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BotRepositoryImpl implements BotRepository {

    private final DSLContext dsl;

    @Override
    public boolean create(Integer id, Long postId) {
        dsl.insertInto(TELEGRAMMESSAGES, TELEGRAMMESSAGES.ID, TELEGRAMMESSAGES.ID_POST)
                .values(id, postId).execute();
        return true;
    }

    @Override
    public boolean remove(Long postId) {
        dsl.deleteFrom(TELEGRAMMESSAGES)
                .where(TELEGRAMMESSAGES.ID_POST.eq((postId))).execute();
        return true;
    }

    @Override
    public Integer get(Long postId) {
        return dsl.select(TELEGRAMMESSAGES.ID).from(TELEGRAMMESSAGES)
                .where(TELEGRAMMESSAGES.ID_POST.eq(postId))
                .fetchOneInto(Integer.class);
    }
}
