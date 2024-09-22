
package pro.ddsr.backend.modules.message.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backend.modules.message.repository.MessageRepository;
import pro.ddsr.backend.modules.message.entity.Message;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    @Transactional
    public Optional<Message> delete(Long id) {
        Optional<Message> optionalMessage = this.messageRepository.findById(id);
        optionalMessage.ifPresent(
            MessageFound -> {
                this.messageRepository.delete(MessageFound);
            }
        );
        return optionalMessage;
    }
 
    public List<Message> findAll() {
        return (List<Message>) this.messageRepository.findAll();
    }

    public Optional<Message> findById(Long id) {
        return this.messageRepository.findById(id);
    }

    public Message save(Message Message) {
        return this.messageRepository.save(Message);
    }

    public Optional<Message> update(Long id, Message message) {
        Optional<Message> optionalMessage = this.messageRepository.findById(id);
        if (optionalMessage.isPresent()) {
            Message messageItem = optionalMessage.orElseThrow();
            messageItem.setContenido(message.getContenido());
            messageItem.setFechaEnvio(message.getFechaEnvio());
            messageItem.setUsuario(message.getUsuario());
            return Optional.of(this.messageRepository.save(messageItem));
        }
        return optionalMessage;
    }
}
