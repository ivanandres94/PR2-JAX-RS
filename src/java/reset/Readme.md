#Proves Curl

```bash

echo "GETS"
echo "Actors"
for i in 1 2 3 4 5 6 7 8 9 10
do
  curl --REQUEST GET http://localhost:8080/PR2-JAX-RS/webresources/actors/$i
  echo
done


echo "Country"
for i in 1 2 3 4 5 6 7 8 9 10
do
  curl --REQUEST GET http://localhost:8080/PR2-JAX-RS/webresources/country/$i
  echo
done


echo "Categories"
for i in 1 2 3 4 5 6 7 8 9 10
do
  curl --REQUEST GET http://localhost:8080/PR2-JAX-RS/webresources/category/$i
  echo
done

echo "Languages"
for i in 1 2 3 4 5 6 7 8 9 10
do
  curl --REQUEST GET http://localhost:8080/PR2-JAX-RS/webresources/language/$i
  echo
done


echo "PUTS"
echo "Actors"
for i in 1 2 3 4 5 6 7 8 9 10
do
  echo first_name_$i last_name_$i
  curl -X PUT -H "Content-Type:application/json" --DATA "{'first_name': '"first_name_$i"', 'last_name': '"last_name_$i"'}" http://localhost:8080/PR2-JAX-RS/webresources/actors/
  echo
done


echo "Country"
for i in 1 2 3 4 5 6 7 8 9 10
do
  echo country_$i
  curl -X PUT -H "Content-Type:application/json" --DATA "{'country': '"country_$i"'}" http://localhost:8080/PR2-JAX-RS/webresources/country/
  echo
done


echo "Categories"
for i in 1 2 3 4 5 6 7 8 9 10
do
  echo category_$i
  curl -X PUT -H "Content-Type:application/json" --DATA "{'name': '"category_$i"'}" http://localhost:8080/PR2-JAX-RS/webresources/category/
  echo
done

echo "Languages"
for i in 1 2 3 4 5 6 7 8 9 10
do
  echo language_$i
  curl -X PUT -H "Content-Type:application/json" --DATA "{'name': '"language_$i"'}" http://localhost:8080/PR2-JAX-RS/webresources/language/
  echo
done
