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
set <caret> = (select Capacity from Venues where VenueNo=e.VenueNo)*2
where Ticket_price <= 5