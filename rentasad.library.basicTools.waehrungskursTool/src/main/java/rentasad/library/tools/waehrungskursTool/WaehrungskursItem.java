package rentasad.library.tools.waehrungskursTool;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
/**
 * 
 * Gustini GmbH (2015)
 * Creation: 15.09.2015
 * Rentasad Library
 * rentasad.lib.tools.waehrungskursTool
 * 
 * @author Matthias Staud
 *
 *
 * Description:
 *
 */
public class WaehrungskursItem
{
	private Date timeStamp;
	private WaehrungenEnum baseCurrencyEnum;
	private final HashMap<WaehrungenEnum, Double> ratesMap = new HashMap<WaehrungenEnum, Double>();
	public WaehrungskursItem(
								Date timeStamp,
								WaehrungenEnum baseCurrencyEnum)
	{
		super();
		this.timeStamp = timeStamp;
		this.baseCurrencyEnum = baseCurrencyEnum;
	}
	/**
	 * @return the ratesMap
	 */
	public HashMap<WaehrungenEnum, Double> getRatesMap()
	{
		return ratesMap;
	}
	/**
	 * @return the timeStamp
	 */
	public Date getTimeStamp()
	{
		return timeStamp;
	}
	/**
	 * @return the baseCurrencyEnum
	 */
	public WaehrungenEnum getBaseCurrencyEnum()
	{
		return baseCurrencyEnum;
	}
	/**
	 * 
	 * Description:ueber die Basiswaehrung wird von gewuenschter Quell- zu Zielwaehrung umgerechnet.
	 * 
	 * @param sourceCurrency
	 * @param targetCurrency
	 * @param sourceValue
	 * @return
	 * Creation: 16.09.2015 by mst
	 */
	public double convertCurrency(WaehrungenEnum sourceCurrency, WaehrungenEnum targetCurrency, double sourceValueDouble)
	{
		
		BigDecimal sourceValue = new BigDecimal(sourceValueDouble);
		BigDecimal sourceRate = new BigDecimal(this.ratesMap.get(sourceCurrency));
		BigDecimal targetRate = new BigDecimal(this.ratesMap.get(targetCurrency));
		/*
		 * Umrechnen in USD (Base-Currency)
		 */
		BigDecimal baseEquivalent = sourceValue.divide(sourceRate,8, RoundingMode.HALF_UP);
		/*
		 * Umrechnen in ZielWaehrung
		 */
		BigDecimal targetEquivalent = baseEquivalent.multiply(targetRate);
//		targetEquivalent = targetEquivalent.setScale(10, RoundingMode.HALF_UP);
		return targetEquivalent.doubleValue();
	}
	
}
