package nuclearkat.normalconversions.currency;

import java.util.List;

public class Currencies {

	public static final Currency APPLE = new Apple();
	public static final Currency MONEY = new Money();

	public static final List<Currency> CURRENCIES = List.of(APPLE, MONEY);
}
