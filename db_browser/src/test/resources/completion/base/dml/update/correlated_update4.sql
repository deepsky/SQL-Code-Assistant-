create table Events (
    id number,
    Ticket_price number,
    VenueNo number
);

create table Venues (
    id number,
    Capacity number,
    VenueNo number
);


update Events e
set Ticket_price = (select Capacity from Venues where <caret>=e.VenueNo)*2
where Ticket_price <= 5