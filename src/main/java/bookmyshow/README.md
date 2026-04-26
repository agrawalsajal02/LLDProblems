# BookMyShow

Source inspiration: `DesignBookMyShow` from the GitLab reference repo.

This version keeps the interview core:
- city -> movie -> theatre -> show -> seat
- discovery flow before booking
- seat availability check
- booking creation with theatre validation

How to remember:
`city se movie -> movie se theatre -> theatre se show -> seat book`

Real-world ownership model:
- `Theatre -> Screen -> Seat`
- `Screen -> Show`
- `Show -> Movie + Screen + Time`

Important distinction:
- screen and seats are permanent infrastructure
- show is a scheduled event on a screen
- theatre can derive its running shows through its screens

Useful APIs:
- `getMoviesByCity(City city)`
- `getTheatresByMovie(City city, String movieId)`
- `getShows(Theatre theatre, String movieId)`
- `getAvailableSeats(Show show)`
- `bookSeats(String bookingId, Theatre theatre, Show show, List<Integer> seatIds)`
