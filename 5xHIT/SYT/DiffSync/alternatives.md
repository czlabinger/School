# Pessemistic
Only edit by one user at a time
Whole file is locked for other users
Refined version would be to only lock subsections but created problems for small files
 - Has to be supported by the application
Not good for unreliable networks

# Edit-Based
Capture *all* edits (Typing, cut, paste, formatting, ...) and mirror them across all devices
If an edit is lost it will create a fork -> two different files

# Three way merge

Client sends changes to server -> server performs 3 way merge -> merge gets send to the clients
If someone changes something while someone else is changing something it will result in a conflict
Not scalable
