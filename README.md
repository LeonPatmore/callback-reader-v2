# Document Reader

Document reader is a REST API for MongoDB, exposing query and insert
endpoints in a useful interface.

## REST API

### Insert

`POST /object?param1=value1`

```json
{
  "key": "value2"
}
```

### Query

You can query based on body, headers and query params.

`GET /object?body.key=value2&params.param1=value1&headers.example-header=header1`

The response is a list of matching objects.

## Development

The makefile should provide everything you need.

### Tests

`make test`

### Run Locally

`make runLocal`

This runs an instance of MongoDB locally in a Docker container.
