package ch.makery.address.model;

import domain.RateDomainModel;
import base.RateDAL;
import org.apache.poi.ss.formula.functions.FinanceLib;

public class Rate extends RateDomainModel {
	
	public static double getPayment(int creditScore, int houseCost, int term)
	{
		double interestRate = RateDAL.getRate(creditScore);
		double paymentM = -1*FinanceLib.pmt(interestRate/1200, term*12, houseCost, 0, false);
		//FinalExam
		//	Normally this kind of method would be in a BLL, but alas...
		
		//	Figure out payment based on:
		//	Interest rate
		//	PV
		//	FV (make FV = 0, unless you want a balloon payment
		//	Compounding = True
		//	Number of Payments (passed in)
		
		
		
		return paymentM;
	}
}
