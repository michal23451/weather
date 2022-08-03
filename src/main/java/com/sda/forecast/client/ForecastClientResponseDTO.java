package com.sda.forecast.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
public class ForecastClientResponseDTO {

    private List<SingleForecastDTO> daily;

    @Getter
    @Setter
    @ToString
    public static class SingleForecastDTO {
        @JsonProperty("dt")
        private long timestamp;
        @JsonProperty("temp")
        private TemperatureDTO temperature;
        private int pressure;
        private int humidity;
        @JsonProperty("wind_speed")
        private double windSpeed;
        @JsonProperty("wind_deg")
        private int windDeg;

        @Getter
        @Setter
        @ToString
        public static class TemperatureDTO {
            private double day;
        }
    }
}



/*
* API: https://openweathermap.org/api/one-call-api
* https://api.openweathermap.org/data/2.5/onecall?lat=50&lon=19&appid=db064a229dcd833fde1a06ea34449c62&exclude=current,minutely,hourly&units=metric
* sample JSON
*

{
	"lat": 50,
	"lon": 19,
	"timezone": "Europe/Warsaw",
	"timezone_offset": 7200,
	"daily": [
		{
			"dt": 1659088800,
			"sunrise": 1659064126,
			"sunset": 1659119518,
			"moonrise": 1659064620,
			"moonset": 1659122820,
			"moon_phase": 0.02,
			"temp": {
				"day": 22.17,
				"min": 15.4,
				"max": 25.68,
				"night": 18.49,
				"eve": 21.96,
				"morn": 15.4
			},
			"feels_like": {
				"day": 22.06,
				"night": 18.8,
				"eve": 22.12,
				"morn": 15.01
			},
			"pressure": 1017,
			"humidity": 62,
			"dew_point": 14.68,
			"wind_speed": 4.51,
			"wind_deg": 50,
			"wind_gust": 9.1,
			"weather": [
				{
					"id": 500,
					"main": "Rain",
					"description": "light rain",
					"icon": "10d"
				}
			],
			"clouds": 94,
			"pop": 1,
			"rain": 1.88,
			"uvi": 5.27
		},
		{
			"dt": 1659175200,
			"sunrise": 1659150610,
			"sunset": 1659205832,
			"moonrise": 1659155280,
			"moonset": 1659210420,
			"moon_phase": 0.05,
			"temp": {
				"day": 17.81,
				"min": 15.66,
				"max": 18.09,
				"night": 15.66,
				"eve": 16.77,
				"morn": 16.56
			},
			"feels_like": {
				"day": 18.07,
				"night": 15.81,
				"eve": 17.04,
				"morn": 16.75
			},
			"pressure": 1014,
			"humidity": 93,
			"dew_point": 16.61,
			"wind_speed": 4.09,
			"wind_deg": 260,
			"wind_gust": 8.6,
			"weather": [
				{
					"id": 501,
					"main": "Rain",
					"description": "moderate rain",
					"icon": "10d"
				}
			],
			"clouds": 100,
			"pop": 1,
			"rain": 7.27,
			"uvi": 0.98
		},
		{
			"dt": 1659261600,
			"sunrise": 1659237094,
			"sunset": 1659292144,
			"moonrise": 1659246000,
			"moonset": 1659297780,
			"moon_phase": 0.08,
			"temp": {
				"day": 14.1,
				"min": 13.03,
				"max": 15.42,
				"night": 13.03,
				"eve": 14.68,
				"morn": 15.01
			},
			"feels_like": {
				"day": 14.07,
				"night": 12.76,
				"eve": 14.55,
				"morn": 15.1
			},
			"pressure": 1014,
			"humidity": 96,
			"dew_point": 13.38,
			"wind_speed": 7.2,
			"wind_deg": 273,
			"wind_gust": 13.5,
			"weather": [
				{
					"id": 501,
					"main": "Rain",
					"description": "moderate rain",
					"icon": "10d"
				}
			],
			"clouds": 100,
			"pop": 1,
			"rain": 18.3,
			"uvi": 0.79
		},
		{
			"dt": 1659348000,
			"sunrise": 1659323578,
			"sunset": 1659378454,
			"moonrise": 1659336660,
			"moonset": 1659385080,
			"moon_phase": 0.12,
			"temp": {
				"day": 22.66,
				"min": 11.5,
				"max": 24.94,
				"night": 14.81,
				"eve": 19.54,
				"morn": 12
			},
			"feels_like": {
				"day": 22.37,
				"night": 14.59,
				"eve": 19.38,
				"morn": 11.55
			},
			"pressure": 1015,
			"humidity": 53,
			"dew_point": 12.58,
			"wind_speed": 3.28,
			"wind_deg": 314,
			"wind_gust": 6.3,
			"weather": [
				{
					"id": 804,
					"main": "Clouds",
					"description": "overcast clouds",
					"icon": "04d"
				}
			],
			"clouds": 100,
			"pop": 0.2,
			"uvi": 6.04
		},
		{
			"dt": 1659434400,
			"sunrise": 1659410063,
			"sunset": 1659464763,
			"moonrise": 1659427380,
			"moonset": 1659472320,
			"moon_phase": 0.15,
			"temp": {
				"day": 23.66,
				"min": 13.32,
				"max": 26.51,
				"night": 17.8,
				"eve": 22.71,
				"morn": 13.32
			},
			"feels_like": {
				"day": 23.52,
				"night": 17.75,
				"eve": 22.73,
				"morn": 13.01
			},
			"pressure": 1019,
			"humidity": 55,
			"dew_point": 13.98,
			"wind_speed": 2.2,
			"wind_deg": 18,
			"wind_gust": 4.1,
			"weather": [
				{
					"id": 802,
					"main": "Clouds",
					"description": "scattered clouds",
					"icon": "03d"
				}
			],
			"clouds": 25,
			"pop": 0.32,
			"uvi": 6.22
		},
		{
			"dt": 1659520800,
			"sunrise": 1659496549,
			"sunset": 1659551070,
			"moonrise": 1659518160,
			"moonset": 1659559620,
			"moon_phase": 0.18,
			"temp": {
				"day": 25.02,
				"min": 15.7,
				"max": 27.15,
				"night": 18.76,
				"eve": 26.87,
				"morn": 15.7
			},
			"feels_like": {
				"day": 24.88,
				"night": 18.36,
				"eve": 26.87,
				"morn": 15.57
			},
			"pressure": 1020,
			"humidity": 50,
			"dew_point": 13.84,
			"wind_speed": 3.5,
			"wind_deg": 117,
			"wind_gust": 4.7,
			"weather": [
				{
					"id": 803,
					"main": "Clouds",
					"description": "broken clouds",
					"icon": "04d"
				}
			],
			"clouds": 69,
			"pop": 0,
			"uvi": 6.79
		},
		{
			"dt": 1659607200,
			"sunrise": 1659583035,
			"sunset": 1659637375,
			"moonrise": 1659609060,
			"moonset": 1659646980,
			"moon_phase": 0.21,
			"temp": {
				"day": 26.34,
				"min": 15.29,
				"max": 29.1,
				"night": 19.73,
				"eve": 29.06,
				"morn": 15.29
			},
			"feels_like": {
				"day": 26.34,
				"night": 19.35,
				"eve": 28.58,
				"morn": 14.62
			},
			"pressure": 1017,
			"humidity": 44,
			"dew_point": 12.94,
			"wind_speed": 3.51,
			"wind_deg": 95,
			"wind_gust": 6.91,
			"weather": [
				{
					"id": 802,
					"main": "Clouds",
					"description": "scattered clouds",
					"icon": "03d"
				}
			],
			"clouds": 44,
			"pop": 0,
			"uvi": 7
		},
		{
			"dt": 1659693600,
			"sunrise": 1659669522,
			"sunset": 1659723678,
			"moonrise": 1659700200,
			"moonset": 1659734580,
			"moon_phase": 0.25,
			"temp": {
				"day": 29.57,
				"min": 17.98,
				"max": 31.89,
				"night": 18.22,
				"eve": 29.62,
				"morn": 17.98
			},
			"feels_like": {
				"day": 28.82,
				"night": 18.53,
				"eve": 30.09,
				"morn": 17.43
			},
			"pressure": 1013,
			"humidity": 36,
			"dew_point": 12.8,
			"wind_speed": 7.39,
			"wind_deg": 326,
			"wind_gust": 10.12,
			"weather": [
				{
					"id": 500,
					"main": "Rain",
					"description": "light rain",
					"icon": "10d"
				}
			],
			"clouds": 5,
			"pop": 0.76,
			"rain": 2.56,
			"uvi": 7
		}
	]
}

*
* */