# Parking Lot Full Requirements Extension

This package adds a fuller parking lot implementation for the exact requirement set:

- multiple levels
- each level has multiple rows
- two vehicle types:
- motorcycles
- cars
- motorcycle can park in **any** available spot
- car can park only in **car** spot
- support:
- `park`
- `unpark`
- `search`

## Folder
- `parkinglot/extensibility/multilevel`

## Final chosen approach
We model the structure like this:

- `ParkingLotFull`
  - has many `ParkingLevel`
- `ParkingLevel`
  - has many `ParkingRow`
- `ParkingRow`
  - has many `ParkingSpot`

This matches the requirement hierarchy directly.

## Core classes
- `Vehicle`
- `ParkingSpot`
- `ParkingRow`
- `ParkingLevel`
- `ParkingLocation`
- `ParkingTicket`
- `ParkingLotFull`

## Compatibility rule

### Motorcycle
Motorcycle can park in:
- motorcycle spot
- car spot

In code:
- `ParkingSpot.canFit(...)`
- if vehicle type is motorcycle -> return true

### Car
Car can park only in:
- car spot

## APIs supported

### `park(Vehicle vehicle)`
- finds first compatible free spot
- marks spot occupied
- returns ticket

### `unpark(String ticketId)`
- removes active ticket
- frees the spot
- returns ticket info

### `searchByTicketId(String ticketId)`
- returns active parking ticket if found

### `searchByVehicleId(String vehicleId)`
- returns active parking ticket for given vehicle

## Search behavior
Search returns ticket object, which includes:
- vehicle id
- vehicle type
- location
- entry time

So caller can see:
- where vehicle is parked
- whether it is still active

## Memory line in Hinglish
- `Lot -> Level -> Row -> Spot`
- `Motorcycle anywhere`
- `Car only car spot`
- `Park pe ticket banta hai`
- `Unpark pe spot free hota hai`

## Important interview answers

### 1. Why separate Level and Row?
Because requirement explicitly says:
- multiple levels
- each level contains multiple rows

### 2. Why keep active maps?
For fast search:
- `ticketId -> ticket`
- `vehicleId -> ticket`

### 3. Why not scan whole lot on search?
Search should be fast.
Linear scan unnecessary hai if active indexes maintain kar sakte hain.

### 4. Why return `ParkingTicket` in search?
Because ticket already contains the useful answer:
- parked location
- vehicle info
- entry time

## Flow

### Park
1. validate vehicle not already parked
2. scan hierarchy for first compatible free spot
3. occupy spot
4. create ticket
5. store active indexes

### Unpark
1. validate ticket exists
2. remove ticket from active maps
3. locate spot using stored location
4. free spot

### Search
1. use active maps
2. return ticket or null

## Extensibility ideas
- pricing
- reserved spots
- electric charging spots
- truck / bus support
- thread safety with per-level or per-row locks
