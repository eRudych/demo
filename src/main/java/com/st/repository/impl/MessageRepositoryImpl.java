package com.st.repository.impl;


import com.st.db.tables.records.MessagesRecord;
import com.st.entity.Message;
import com.st.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

import static com.st.db.tables.Messages.MESSAGES;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MessageRepositoryImpl implements MessageRepository {

    private final DSLContext dsl;

    private Long insert(Message message) {
        MessagesRecord messagesRecord = dsl.insertInto(MESSAGES, MESSAGES.NAME, MESSAGES.PHONE, MESSAGES.MESSAGE,MESSAGES.CREATED_AT)
                .values(message.getName(), message.getPhone(), message.getMessage(), message.getDate())
                .returning(MESSAGES.ID)
                .fetchOne();
        message.setId(messagesRecord.getValue( MESSAGES.ID));
        log.info("Insert into db: {}", message.toString());
        return messagesRecord.getValue(MESSAGES.ID);
    }

    @Override
    public boolean create(Message message) {
        try {
            insert(message);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean remove(Long id) {
        try {
            dsl.deleteFrom(MESSAGES)
                    .where(MESSAGES.ID.eq(id)).execute();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<Message> getAll() {
        return dsl.selectFrom(MESSAGES).orderBy(MESSAGES.ID.desc())
                .fetch(r->{
                    Message message = new Message();
                    message.setId(r.get(0,Long.class));
                    message.setName(r.get(1,String.class));
                    message.setPhone(r.get(2,String.class));
                    message.setMessage(r.get(3,String.class));
                    message.setDate(r.get(4, Timestamp.class));
                    return  message; } );
    }
}
