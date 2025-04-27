## Camino

Simple JSON based rule engine.

Sample JSON:
```json
{
	"name": "myFirstFlow",
	"blocks": [
		{
			"id": "635423435534534",
			"type": "START",
			"nextId": "54334#$GT^&345#$5"
		},
		{
			"id": "54334#$GT^&345#$5",
			"name": "firstAction",
			"type": "ACTION",
			"action": "firstActionHandler",
			"nextId": "87634#$GT^&345#$5"
		},
		{
			"id": "87634#$GT^&345#$5",
			"name": "secondAction",
			"type": "ACTION",
			"action": "secondActionHandler",
			"nextId": "1827634#$GT^&345#$5"
		},
		{
			"id": "1827634#$GT^&345#$5",
			"name": "secondAction",
			"type": "INTERSECTION",
			"conditions": [
				{
					"name": "yes",
					"expression": "name.equals('Alex')",
					"nextId": "65476564745"
				},
				{
					"name": "no",
					"defaultCondition": true,
					"nextId": "97866544536"
				}
			]
		},
				{
			"id": "65476564745",
			"name": "thirdAction",
			"type": "ACTION",
			"action": "thirdActionHandler",
			"nextId": "33232233232232"
		},
		{
			"id": "97866544536",
			"name": "forthAction",
			"type": "ACTION",
			"action": "forthActionHandler",
			"nextId": "33232233232232"
		},
		{
			"id": "33232233232232",
			"type": "END"
		}
	]
}
```

How to:
1. Add the dependency to your preferred build tool.
   For example if you are using maven just add the dependency to your pom.xml .
2. Create your own JSON flows and add them under src/main/resources/****.json .
   This path is configurable under the following property "camino.path".
3. Start your application and camino will automatically load your flow(s).
