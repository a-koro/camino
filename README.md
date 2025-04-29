## Camino

Simple JSON based rule engine.

Sample JSON:
```json
{
	"name": "myFirstFlow",
	"blocks": [
		{
			"id": "f2966cbd86464397a977d7c4bcea8d05",
			"type": "START",
			"nextId": "e21464b9053b424580d56f25430d284f"
		},
		{
			"id": "e21464b9053b424580d56f25430d284f",
			"name": "firstAction",
			"type": "ACTION",
			"action": "firstActionHandler",
			"nextId": "ae390e6cf3714b5c8e01bc954bd49c96"
		},
		{
			"id": "ae390e6cf3714b5c8e01bc954bd49c96",
			"name": "secondAction",
			"type": "ACTION",
			"action": "secondActionHandler",
			"nextId": "100f6e2d709943d79ea105a2b32b3cc4"
		},
		{
			"id": "100f6e2d709943d79ea105a2b32b3cc4",
			"name": "firstIntersection",
			"type": "INTERSECTION",
			"conditions": [
				{
					"name": "yes",
					"expression": "name.equals('Alex')",
					"nextId": "3a18a8c9f1864a1b8e11751c4e76fef8"
				},
				{
					"name": "no",
					"defaultCondition": true,
					"nextId": "fa337eb4ecf14032ae19b5cff2c42448"
				}
			]
		},
				{
			"id": "3a18a8c9f1864a1b8e11751c4e76fef8",
			"name": "thirdAction",
			"type": "ACTION",
			"action": "thirdActionHandler",
			"nextId": "446456cd6c74483780e375d6d63696cb"
		},
		{
			"id": "fa337eb4ecf14032ae19b5cff2c42448",
			"name": "forthAction",
			"type": "ACTION",
			"action": "forthActionHandler",
			"nextId": "446456cd6c74483780e375d6d63696cb"
		},
		{
			"id": "446456cd6c74483780e375d6d63696cb",
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
3. Add the following property spring.camino.enabled=true in your application.properties file.
4. Start your application and camino will automatically load your flow(s).
