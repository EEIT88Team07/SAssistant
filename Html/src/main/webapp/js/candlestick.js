function getCandlestick(json) {

	var myChart = echarts.init(document.getElementById('candlestick'));

	// 指定圖表的配置相和數據,開盤，收盤，最低，最高
	// [ '2013/1/24', 2320.26, 2320.26, 2287.3, 2362.94 ]

	var keyArray = new Array();
	var jsonArray = new Array();
	var i = 0;
	for ( var key in json) {
		if (json.hasOwnProperty(key)) {
			keyArray.push(key)
		}
	}
	keyArray.sort();

	for (var i = 0; i < keyArray.length; i++) {
		var arrayObject = new Array();
		arrayObject.push(keyArray[i]);
		arrayObject.push(json[keyArray[i]][0]);
		arrayObject.push(json[keyArray[i]][1]);
		arrayObject.push(json[keyArray[i]][2]);
		arrayObject.push(json[keyArray[i]][3]);
		jsonArray.push(arrayObject);
	}
	var data0 = splitData(jsonArray);
	function splitData(rawData) {
		var categoryData = [];
		var values = []
		for (var i = 0; i < rawData.length; i++) {
			categoryData.push(rawData[i].splice(0, 1)[0]);
			values.push(rawData[i])
		}
		return {
			categoryData : categoryData,
			values : values
		};
	}

	function calculateMA(dayCount) {
		var result = [];
		for (var i = 0, len = data0.values.length; i < len; i++) {
			if (i < dayCount) {
				result.push('-');
				continue;
			}
			var sum = 0;
			for (var j = 0; j < dayCount; j++) {
				sum += data0.values[i - j][1];
			}
			result.push(sum / dayCount);
		}
		return result;
	}

	option = {
		title : {
			text : '上證指數',
			left : 0
		},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				type : 'line'
			}
		},
		legend : {
			data : [ '日K', 'MA5', 'MA10', 'MA20', 'MA30' ]
		},
		grid : {
			left : '10%',
			right : '10%',
			bottom : '15%'
		},
		xAxis : {
			type : 'category',
			data : data0.categoryData,
			scale : true,
			boundaryGap : false,
			axisLine : {
				onZero : false
			},
			splitLine : {
				show : false
			},
			splitNumber : 20,
			min : 'dataMin',
			max : 'dataMax'
		},
		yAxis : {
			scale : true,
			splitArea : {
				show : true
			}
		},
		dataZoom : [ {
			type : 'inside',
			start : 50,
			end : 100
		}, {
			show : true,
			type : 'slider',
			y : '90%',
			start : 50,
			end : 100
		} ],
		series : [
				{
					name : '日K',
					type : 'candlestick',
					data : data0.values,
					markPoint : {
						label : {
							normal : {
								formatter : function(param) {
									return param != null ? Math
											.round(param.value) : '';
								}
							}
						},
						data : [ {
							name : 'XX標點',
							coord : [ '2013/5/31', 2300 ],
							value : 2300,
							itemStyle : {
								normal : {
									color : 'rgb(41,60,85)'
								}
							}
						}, {
							name : 'highest value',
							type : 'max',
							valueDim : 'highest'
						}, {
							name : 'lowest value',
							type : 'min',
							valueDim : 'lowest'
						}, {
							name : 'average value on close',
							type : 'average',
							valueDim : 'close'
						} ],
						tooltip : {
							formatter : function(param) {
								return param.name + '<br>'
										+ (param.data.coord || '');
							}
						}
					},
					markLine : {
						symbol : [ 'none', 'none' ],
						data : [ [ {
							name : 'from lowest to highest',
							type : 'min',
							valueDim : 'lowest',
							symbol : 'circle',
							symbolSize : 10,
							label : {
								normal : {
									show : false
								},
								emphasis : {
									show : false
								}
							}
						}, {
							type : 'max',
							valueDim : 'highest',
							symbol : 'circle',
							symbolSize : 10,
							label : {
								normal : {
									show : false
								},
								emphasis : {
									show : false
								}
							}
						} ], {
							name : 'min line on close',
							type : 'min',
							valueDim : 'close'
						}, {
							name : 'max line on close',
							type : 'max',
							valueDim : 'close'
						} ]
					}
				}, {
					name : 'MA5',
					type : 'line',
					data : calculateMA(5),
					smooth : true,
					lineStyle : {
						normal : {
							opacity : 0.5
						}
					}
				}, {
					name : 'MA10',
					type : 'line',
					data : calculateMA(10),
					smooth : true,
					lineStyle : {
						normal : {
							opacity : 0.5
						}
					}
				}, {
					name : 'MA20',
					type : 'line',
					data : calculateMA(20),
					smooth : true,
					lineStyle : {
						normal : {
							opacity : 0.5
						}
					}
				}, {
					name : 'MA30',
					type : 'line',
					data : calculateMA(30),
					smooth : true,
					lineStyle : {
						normal : {
							opacity : 0.5
						}
					}
				},

		]
	};

	// 使用剛指定的配置項和數據顯示圖表。
	myChart.setOption(option);
}