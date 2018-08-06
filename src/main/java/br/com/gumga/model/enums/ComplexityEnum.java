package br.com.gumga.model.enums;

public enum ComplexityEnum {
	
	VERY_WEAK("Muito fraco", 0, 20),
	WEAK("Fraco", 20, 40),
	GOOD("Bom", 40, 60),
	STRONG("Forte", 60, 80),
	VERY_STRONG("Muito forte", 80, 100);
	
	private String label;
	private Integer minValue;
	private Integer maxValue;

	private ComplexityEnum(String label, Integer minValue, Integer maxValue) {
		this.label = label;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	public String getLabel() {
		return label;
	}

	public Integer getMinValue() {
		return minValue;
	}

	public Integer getMaxValue() {
		return maxValue;
	}

	public static ComplexityEnum getFromScore(Integer score) {
		score = score < 0 ? 0 : score > 100 ? 100 : score;
		
		if (score >= VERY_WEAK.minValue && score < VERY_WEAK.maxValue) return VERY_WEAK; 
		if (score >= WEAK.minValue && score < WEAK.maxValue) return WEAK; 
		if (score >= GOOD.minValue && score < GOOD.maxValue) return GOOD; 
		if (score >= STRONG.minValue && score < STRONG.maxValue) return STRONG; 
		if (score >= VERY_STRONG.minValue && score <= VERY_STRONG.maxValue) return VERY_STRONG; 
		
		return null;
	}
	
}
