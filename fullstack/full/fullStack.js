//given code 
// import React, { useEffect, useState } from "react";
// import axios from "axios";
// import "./App.css";

// function Home() {
//   const [data, setData] = useState([]);
//   const [rating, setRating] = useState(1);
//   const [refresh, setrefresh] = useState(1);

//   const handleApply = async (id) => {
//     // Write your code here
//   };

//   const handleRating = () => {
//     // Write your code here
//   };

//   const handleAddRating = async (id) => {
//     // Write your code here
//   };

//   const handleDrop = async (id) => {
//     // Write your code here
//   };
//   return (
//     <div className="home">
//       <header>
//         <h2>ABC Learning</h2>
//       </header>
//       {/* write your code here */}
//       <div className="cardContainer">

//         <div className="card">
//           <ul>
//             <div className="header">
//               {/* course name */}
//               <li data-testid="course-name"></li>
//               {/* course dept */}
//               <li data-testid="course-dept"></li>
//               {/* course description */}
//               <li data-testid="course-description"></li>
//               <li>
//                 <>
//                   Rate:
//                   <select
//                     className="rating"
//                     name="rating"
//                     data-testid="select-box"
//                   >
//                     <option>1</option>
//                     <option>2</option>
//                     <option>3</option>
//                     <option>4</option>
//                     <option>5</option>
//                   </select>
//                   <button className="rate" data-testid="add-rate">
//                     Add
//                   </button>
//                 </>

//                 <button className="drop" data-testid="drop">
//                   Drop Course
//                 </button>
//               </li>
//               <li>
//                 <button className="btn" data-testid="apply">
//                   Apply
//                 </button>
//               </li>
//             </div>
//             <div className="footer">
//               {/* footer contents */}
//               <li data-testid="footer"></li>
//             </div>
//           </ul>
//         </div>

//       </div>
//     </div>
//   );
// }

// export default Home;


//function base react
import React, { useEffect, useState } from "react";
import axios from "axios";
import "./App.css";

function Home() {
  const url = "http://localhost:8001/courses/";
  const [show, setShow] = useState(false);
  const [data, setData] = useState([]);
  const [rating, setRating] = useState(1);

  useEffect(() => {
    handleGetData();
  }, []);

  const handleGetData = () => {
    axios
      .get(url + "get")
      .then((json) => {
        setData(json.data);
      })
      .catch((error) => console.error("Error fetching data:", error));
  };

  const handleApply = async (id) => {
    axios
      .post(url + "enroll/" + id)
      .then(() => {
        handleGetData();
      })
      .catch((error) => console.error("Error applying:", error));
  };

  const handleRating = (e) => {
    setRating(e.target.value);
  };

  const handleAddRating = async (id) => {
    axios
      .patch(url + "rating/" + id, { rating: +rating })
      .then(() => {
        handleGetData();
      })
      .catch((error) => console.error("Error adding rating:", error));
  };

  const handleDrop = async (id) => {
    axios
      .delete(url + "drop/" + id)
      .then(() => {
        handleGetData();
      })
      .catch((error) => console.error("Error dropping course:", error));
  };

  return (
    <div className="home">
      <header>
        <h2>ABC Learning</h2>
      </header>
      <div className="cardContainer">
        {data.map((courses) => {
          return (
            <div className="card" key={courses._id}>
              <ul>
                <div className="header">
                  <li data-testid="course-name">{courses.courseName}</li>
                  <li data-testid="course-dept">{courses.courseDept}</li>
                  <li data-testid="course-description">
                    {courses.description}
                  </li>
                  {courses.isApplied && (
                    <ul>
                      {!courses.isRated && (
                        <li>
                          Rate:
                          <select
                            className="rating"
                            name="rating"
                            data-testid="select-box"
                            onChange={handleRating}
                          >
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                          </select>
                          <button
                            className="rate"
                            data-testid="add-rate"
                            onClick={() => handleAddRating(courses._id)}
                          >
                            Add
                          </button>
                        </li>
                      )}
                      {courses.isApplied && (
                        <button
                          className="drop"
                          data-testid="drop"
                          onClick={() => handleDrop(courses._id)}
                        >
                          Drop Course
                        </button>
                      )}
                    </ul>
                  )}
                  {!courses.isApplied && (
                    <li>
                      <button
                        className="apply"
                        data-testid="apply"
                        onClick={() => handleApply(courses._id)}
                      >
                        Apply
                      </button>
                    </li>
                  )}
                </div>

                <div className="footer">
                  <li data-testid="footer">
                    {courses.duration}hrs.{courses.noOfRatings} Ratings .{" "}
                    {courses.rating}/5
                  </li>
                </div>
              </ul>
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default Home;

//??????????????/NOdeJS1!!!!!!

const express = require("express");
const Course = require("../mongoose/models/courses");

//setting up the student router
const usersRouter = new express.Router();

//write your code here

usersRouter.get("/courses/get", async (req, res) => {
  const data = await Course.find();
  res.status(200).json(data);
});
usersRouter.post("/courses/enroll/:id", async (req, res) => {
  const id = req.params.id;
  const getData = await Course.findById(id);
  if (!getData.isApplied) {
    po = {
      isApplied: true,
    };
    await Course.findByIdAndUpdate(id, po);
    res.status(200).json({ message: "Sucessfully" });
  } else {
    res.status(403).json({ message: "Not" });
  }
});

usersRouter.patch("/courses/rating/:id", async (req, res) => {
  const id = req.params.id;
  const getData = await Course.findById(id);
  if (!getData.isRated && getData.isApplied) {
    const body = req.body;
    const noOfRatings = getData.noOfRatings + 1;
    const finalRate = (
      (getData.noOfRatings * getData.rating + body.rating) /
      noOfRatings
    ).toFixed(1);
    pa = {
      isRated: true,
      rating: finalRate,
      noOfRatings: noOfRatings,
    };
    await Course.findByIdAndUpdate(id, pa);
    res.status(200).json({ message: "Sucessfully" });
  } else {
    res.status(403).json({ message: "Not" });
  }
});

usersRouter.delete("/courses/drop/:id", async (req, res) => {
  const id = req.params.id;
  const getData = await Course.findById(id);
  if (getData.isApplied) {
    de = {
      isApplied: false,
    };
    await Course.findByIdAndUpdate(id, de);
    res.status(200).json({ message: "Sucessfully" });
  } else {
    res.status(403).json({ message: "Not" });
  }
});
module.exports = usersRouter;





