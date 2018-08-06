package br.com.gumga.model;

import java.io.Serializable;

public class AvaliationResult implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer score;
	private String tip;

	public AvaliationResult(Integer score, String tip) {
		super();
		this.score = score;
		this.tip = tip;
	}

	public Integer getScore() {
		return score < 0 ? 0 : score > 100 ? 100 : score;
	}

	public String getTip() {
		return tip;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((score == null) ? 0 : score.hashCode());
		result = prime * result + ((tip == null) ? 0 : tip.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AvaliationResult other = (AvaliationResult) obj;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		if (tip == null) {
			if (other.tip != null)
				return false;
		} else if (!tip.equals(other.tip))
			return false;
		return true;
	}
	
}
