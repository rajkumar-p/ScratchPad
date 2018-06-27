%%%-------------------------------------------------------------------
%%% @author rajkumar.p
%%% @copyright (C) 2018, Free as in free speech
%%% @doc
%%%
%%% @end
%%% Created : 27. Jun 2018 10:17 PM
%%%-------------------------------------------------------------------
-module(basics).
-author("rajkumar.p").

%% API
-export([
          pie/0, print/1,
          either_or_both/2,
          area/1, new_area/1,
          sign/1, do_sum/1
        ]).

pie() ->
  3.14.

print(Term) ->
  io:format("The value of Term is: ~p.~n", [Term]).

either_or_both(true, B) when is_boolean(B) ->
  true;
either_or_both(A, true) when is_boolean(A) ->
  true;
either_or_both(false, false) ->
  false.

area({circle, Radius}) ->
  Radius * Radius * math:pi();
area({square, Side}) ->
  Side * Side;
area({rectangle, Height, Width}) ->
  Height * Width.

new_area(Shape) ->
  case Shape of
    {circle, Radius} ->
      Radius * Radius * math:pi();
    {square, Side} ->
      Side * Side;
    {rectangle, Height, Width} ->
      Height * Width
  end.

sign(N) when is_number(N) ->
  if
    N > 0 -> positive;
    N < 0 -> negative;
    true ->  zero
  end.

do_sum(N) -> do_sum(N, 0).

do_sum(0, Total) ->
  Total;
do_sum(N, Total) ->
  do_sum(N - 1, Total + N).
