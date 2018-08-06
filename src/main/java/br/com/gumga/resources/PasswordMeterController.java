package br.com.gumga.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.gumga.model.AvaliationResult;
import br.com.gumga.model.enums.ComplexityEnum;
import br.com.gumga.utils.Addition;
import br.com.gumga.utils.Deduction;

@RestController
@RequestMapping(value="/meter")
public class PasswordMeterController {

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/{senha}")	
	public AvaliationResult avaliarResultado(@PathVariable("senha") String senha ) {
		int calculatedAdditionValue = new Addition(senha).getAdditionCalculatedValue();
		int calculatedDeductionValue = new Deduction(senha).getDeductionCalculatedValue();
		int score = calculatedAdditionValue - calculatedDeductionValue;
		
		return new AvaliationResult(score, ComplexityEnum.getFromScore(score).getLabel());
	}
	
}
