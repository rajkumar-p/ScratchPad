''' Circuitous, LLC -
    An Advanced Circle Analytics Company

'''

import math
from random import random, seed

class Circle(object):
  'An Advanced Circle Analytics Company'

  version = '0.1'

  # Not a constructor but an initializer as the instance
  # has already been constructed
  def __init__(self, radius):
    self.radius = radius

  def area(self):
    'Perform quadrature on a shape of uniform radius'
    return math.pi * self.radius ** 2.0

  def perimeter(self):
    return 2.0 * math.pi * self.radius

  @staticmethod
  def angle_to_grade(angle):
    'Convert angle in degree to a percentage grade'
    return math.tan(math.radian(angle)) * 100.0

  @classmethod # Alternative constructor
  def from_bbd(cls, bbd):
    'Construct a circle from a bounding box diagonal'
    radius = bbd / 2.0 / math.sqrt(2.0)
    return cls(radius)

class Tire(Circle):
  'Tires are circles with a corrected parameter'

  def perimeter(self):
    'Circumference corrected for the rubber'
    return Circle.perimeter(self) * 1.25

if __name__ == "__main__":
  'Need to seed to reproduce results'
  seed(8675309)
  print 'Circuitous version', Circle.version
  n = 10
  circles = [Circle(random()) for i in xrange(n)]
  print 'The average area of', n, 'random circles'
  avg = sum([c.area() for c in circles]) / n
  print avg

  cuts = [0.1, 0.7, 0.8]
  circles = [Circle(r) for r in cuts]
  for c in circles:
    print 'A circlet with a radius of', c.radius
    print 'has a perimeter of', c.perimeter()
    print 'and a cold area of', c.area()
    c.radius *= 1.1
    print 'and a warm area of', c.area()
    print

  '__init__ is first looked up in Perimeter and then Circle'
  'radius is first looked up in Perimeter and then circle'
  t = Tire(22)
  print 'A tire of radius', t.radius
  print 'has a inner area of', t.area()
  print 'and an odometer corrected permiter of', t.perimeter()
  print

  print dict.fromkeys(['raymond', 'rachel', 'matthew'])
  print

  c = Circle.from_bbd(25.1)
  print 'A circle with a bbd of 25.1'
  print 'has a radius of', c.radius
  print 'and an area of', c.area()
  print

  t = Tire.from_bbd(45)
  print 'A tire of radius', t.radius
  print 'has a inner area of', t.area()
  print 'and an odometer corrected permiter of', t.perimeter()
  print