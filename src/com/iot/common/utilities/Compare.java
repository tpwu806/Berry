package com.iot.common.utilities;

import com.iot.threshold.dto.ThresholdDO;

public class Compare {
	
   
    
	public static Integer compareClass(String tem,ThresholdDO thresholdDO){
		Integer temperature= Integer.valueOf(tem);
		
		if(temperature > thresholdDO.getMintemperature() 
				&& temperature < thresholdDO.getMaxtemperature()){
			return 0;
			
		}
		return 1;		
	}
}
