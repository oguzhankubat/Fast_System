package Finance.Fast_System.Utilities;

import java.util.Random;

import org.springframework.stereotype.Component;


@Component
public class TransactionNumberGenerator {

    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new Random();

    public String generateTransactionNumber() {
        StringBuilder transactionNumber = new StringBuilder();

        transactionNumber.append(LETTERS.charAt(RANDOM.nextInt(LETTERS.length())));
        transactionNumber.append(LETTERS.charAt(RANDOM.nextInt(LETTERS.length())));


        for (int i = 0; i < 28; i++) {
            transactionNumber.append(RANDOM.nextInt(10));
        }

        return transactionNumber.toString();
    }

}

