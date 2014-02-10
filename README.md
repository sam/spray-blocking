# Spray Blocking Requests

An example of blocking, sequential requests in Spray (undesired behavior).

Our requests take 5 seconds to complete (scheduled with the `Waiter` Actor).

If you make multiple requests they'll "stack". So 4 requests will take 20 seconds
to complete. This was unexpected behavior for me (at least in run mode outside of testing).

To reproduce the issue you can run a server:

    sbt
    > re-start

And then make multiple requests through your web-browser to http://localhost:9000/

Or you can run the tests:

    sbt
    > test
