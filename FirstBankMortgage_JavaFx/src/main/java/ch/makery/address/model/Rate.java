package ch.makery.address.model;

import domain.RateDomainModel;
import base.RateDAL;
import org.apache.poi.ss.formula.functions.FinanceLib;

public class Rate extends RateDomainModel {
	
	public static double getPayment(int creditScore, int houseCost, int loanValue)
	{
		double interestRate = RateDAL.getRate(houseCost);
		double paymentM = -1*FinanceLib.pmt(interestRate/12, creditScore*12, loanValue, 0, true);
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
