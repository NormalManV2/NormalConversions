package nuclearkat.normalconversions.conversion;

import nuclearkat.normalconversions.currency.Currency;

import java.util.UUID;

public class PlayerConversion {

	private final UUID uuid;
	private Currency from;
	private Currency to;
	private int amount;

	public PlayerConversion(UUID uuid) {
		this.uuid = uuid;
	}

	public void update(Currency from, Currency to, int amount) {
		this.from = from;
		this.to = to;
		this.amount = amount;
	}

	public UUID getUUID() {
		return uuid;
	}

	public Currency getFrom() {
		return from;
	}

	public Currency getTo() {
		return to;
	}

	public int getAmount() {
		return amount;
	}

	public void setFrom(Currency from) {
		this.from = from;
	}

	public void setTo(Currency to) {
		this.to = to;
	}

	public void decreaseAmount(int amount) {
		this.amount -= amount;
	}

	public void increaseAmount(int amount) {
		this.amount += amount;
	}
}
