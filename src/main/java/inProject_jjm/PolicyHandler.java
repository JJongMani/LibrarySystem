package inProject_jjm;

import inProject_jjm.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{
    @Autowired
    LibrarySystemRepository librarySystemRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReservationCanceled_TotalCountChange(@Payload ReservationCanceled reservationCanceled){

        if(reservationCanceled.isMe()){
            System.out.println("##### listener TotalCountMinus : " + reservationCanceled.toJson());
            System.out.println("2 ##" + librarySystemRepository + "##");
            Optional<LibrarySystem> librarySystemOptional = librarySystemRepository.findById((long) 1);
            LibrarySystem librarySystem = librarySystemOptional.get();
            System.out.println("3 ##" + librarySystem + "##");
            librarySystem.setTotalCount(librarySystem.getTotalCount() - 1);

            librarySystemRepository.save(librarySystem);
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentCompleted_TotalCountChange(@Payload PaymentCompleted paymentCompleted){

        if(paymentCompleted.isMe()){
            System.out.println("##### listener TotalCountPlus : " + paymentCompleted.toJson());
            System.out.println("2 ##" + librarySystemRepository + "##");
            Optional<LibrarySystem> librarySystemOptional = librarySystemRepository.findById((long) 1);
            LibrarySystem librarySystem = librarySystemOptional.get();
            System.out.println("3 ##" + librarySystem + "##");
            librarySystem.setTotalCount(librarySystem.getTotalCount() + 1);

            librarySystemRepository.save(librarySystem);
        }
    }

}
