CREATE UNIQUE INDEX event_slug_key ON event(slug);
CREATE UNIQUE INDEX attendee_event_id_email_key ON attendee(event_id, email);
CREATE UNIQUE INDEX check_in_attendee_id_key ON check_in(attendee_id);