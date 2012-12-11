package sample.spring.chapter05.springbankapp;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sample.spring.chapter05.springbankapp.domain.FixedDepositDetails;
import sample.spring.chapter05.springbankapp.service.AccountStatementService;
import sample.spring.chapter05.springbankapp.service.CustomerRegistrationService;
import sample.spring.chapter05.springbankapp.service.FixedDepositService;

public class SpringBankApp {
	private static Logger logger = Logger.getLogger(SpringBankApp.class);

	public static void main(String args[]) throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				SpringBankAppConfiguration.class);

		logger.info("----------> Beginning with accessing CustomerRegistrationService");
		CustomerRegistrationService customerRegistrationService_1 = (CustomerRegistrationService) context
				.getBean("customerRegistrationService");
		customerRegistrationService_1.setAccountNumber("account_1");
		customerRegistrationService_1.setAddress("address_1");
		customerRegistrationService_1.setDebitCardNumber("debitCardNumber_1");
		customerRegistrationService_1.register();
		logger.info("----------> Done with accessing CustomerRegistrationService");

		logger.info("----------> Beginning with accessing FixedDepositService");
		FixedDepositService fixedDepositService = context
				.getBean(FixedDepositService.class);
		fixedDepositService.createFixedDeposit(new FixedDepositDetails(1, 1000,
				12, "someemail@somedomain.com"));
		logger.info("----------> Done with accessing FixedDepositService");

		logger.info("----------> Beginning with accessing AccountStatementService");
		try {
			AccountStatementService accountStatementService = context
					.getBean(AccountStatementService.class);
			accountStatementService.getAccountStatement(new Date(), new Date());
		} catch (Exception e) {
			logger.error("Exception : " + e.toString());
		}
		logger.info("----------> Done with accessing AccountStatementService");
	}
}