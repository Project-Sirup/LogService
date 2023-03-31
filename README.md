# LogService

This is part of Project-Sirup.

DONT LOG ANY SENSITIVE INFO!!!

## Endpoints

- [Debug](#debug)
- [Info](#info)
- [Warn](#warn)
- [Error](#error)

## Request-Response

All endpoints take a `LogRequest`, which contains a `message`.

Any successful log will return the code `200`.

Any failed log will return the code `400`.

### Debug

Logs any information during debugging. Should not be used for final release!
Debug only logs to console and will not create persistent logs.
These logs will be colored blue.

### Info

Logs any info during runtime. Should be used for any successful action.
Info logs are persistent.
There logs will be colored green.

### Warn

Logs any warning during runtime. Should be used for any failed action.
Warn Logs are persistent.
There logs will be colored yellow.

### Error

Logs any fatal error. Should only be used when action breaks the system.
Error logs are persistent.
These logs will be colored red.
