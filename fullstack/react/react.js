import React, { useState } from "react";

function App() {
  const [newTitle, setNewTitle] = useState("");
  const [newDescription, setNewDescription] = useState("");
  const [newTopic, setNewTopic] = useState("");
  const [addedAgenda, setAddedAgenda] = useState([
    {
      title: "Angular",
      description: "Some description about the angular",
      topics: [
        "Introduction",
        "Typescript",
        "Why Angular?",
        "Understanding Versions",
        "Fundamentals",
      ],
    },
    {
      title: "Vue",
      description: "Some description about the vue",
      topics: [
        "Introduction",
        "Javascript",
        "Why Vue?",
        "Vue Bindings",
        "Component Interaction",
      ],
    },
  ]);
  const [addedTopic, setAddedTopic] = useState([]);
  const [showAgendaBlock, setShowAgendaBlock] = useState(false);

  const handleTitleChange = (e) => {
    setNewTitle(e.target.value);
  };
  
  const handleDescriptionChange = (e) => {
    setNewDescription(e.target.value);
  };
  
  const handleTopicChange = (e) => {
    setNewTopic(e.target.value);
  };
  
  const addTopic = () => {
    setAddedTopic([...addedTopic, newTopic]);
    setNewTopic("");
  };

  const addAgenda = () => {
    const agenda = {
      title: newTitle,
      description: newDescription,
      topics: addedTopic,
    };

    setAddedAgenda([...addedAgenda, agenda]);
    setNewTitle("");
    setNewDescription("");
    setNewTopic("");
    setAddedTopic([]);
  };

  const preventSubmit = (e) => {
    e.preventDefault();
  };

  const showAgendaBlockfn = () => {
    setShowAgendaBlock(!showAgendaBlock);
  };

  return (
    <div>
      <h1 className="mx-5 mb-5">Agenda Manager</h1>
      {!showAgendaBlock && (
        <div className="container" role="addAgenda">
          <button
            className="btn btn-info"
            role="goToView"
            onClick={showAgendaBlockfn}
          >
            Click To View
          </button>
          <form onSubmit={preventSubmit}>
            <div className="my-3">
              <label className="form-label">Title</label>
              <input
                type="text"
                name="newTitle"
                placeholder="Enter the title"
                className="form-control"
                role="inputTitle"
                value={newTitle}
                onChange={handleNewTitle}
              />
              <small className="text-danger" data-testid="invalidTitle">
                {newTitle.trim().length === 0 ? "Title is required" : ""}
              </small>
            </div>
            <div className="my-3">
              <label className="form-label">Description</label>
              <input
                type="text"
                name="newDescription"
                placeholder="Enter the description"
                className="form-control"
                role="inputDescription"
                value={newDescription}
                onChange={handleDescriptionChange}
              />
              <small className="text-danger" data-testid="invalidDescription">
                {newDescription.trim().length === 0
                  ? "Description is required"
                  : ""}
              </small>
            </div>
            <div className="my-3 w-50">
              <label className="form-label">Enter topic</label>
              <input
                type="text"
                name="newTopic"
                placeholder="Enter the topic"
                className="form-control"
                role="inputTopic"
                value={newTopic}
                onChange={handleTopicChange}
              />
              <small className="text-danger" data-testid="invalidTopic">
                {newTopic.trim().length === 0 && addedTopic.length === 0
                  ? "Topic is required"
                  : ""}
              </small>
            </div>
            <button
              className="btn btn-success addAlign"
              role="addTopicBtn"
              onClick={addTopic}
              disabled={newTopic.trim().length === 0}
            >
              + Add Topic
            </button>
            <button
              className="btn btn-success submitAlign"
              role="submitAgendaBtn"
              onClick={addAgenda}
              disabled={
                newTitle.trim().length === 0 ||
                newDescription.trim().length === 0 ||
                addedTopic.length === 0
              }
            >
              Submit Agenda
            </button>
          </form>
          {addedTopic.length === 0 && (
            <div className="text-danger ml-2 mt-5" data-testid="noTopicsMsg">
              No Topics Added
            </div>
          )}
          {addedTopic.length !== 0 && (
            <div className="card my-3">
              <div className="card-header">Added Topics</div>
              <div className="card-body">
                <ul className="list-group">
                  {addedTopic.map((topic, index) => (
                    <li
                      key={index}
                      className="list-group-item"
                      role="topicList"
                    >
                      {topic}
                    </li>
                  ))}
                </ul>
              </div>
              <div className="card-footer">Refer the topics you added</div>
            </div>
          )}
        </div>
      )}
      {showAgendaBlock && (
        <div className="container" role="viewAgenda">
          <button
            className="btn btn-info"
            role="goToAdd"
            onClick={showAgendaBlockfn}
          >
            Click To Add Agenda
          </button>
          {addedAgenda.map((agenda, index) => (
            <div key={index} className="card my-3" role="cards">
              <div className="card-header">{agenda.title}</div>
              <div className="card-body">
                <ul className="list-group">
                  {agenda.topics.map((topic, idx) => (
                    <li key={idx} className="list-group-item">
                      {topic}
                    </li>
                  ))}
                </ul>
              </div>
              <div className="card-footer">{agenda.description}</div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default App;



//submitted code
// import React, { useState } from "react";

// function App() {
//   const [newTitle, setNewTitle] = useState("");
//   const [newDescription, setNewDescription] = useState("");
//   const [newTopic, setNewTopic] = useState("");
//   const [addedAgenda, setAddedAgenda] = useState([
//     {
//       title: "Angular",
//       description: "Some description about the angular",
//       topics: ["Introduction", "Typescript", "Why Angular?", "Understanding Versions", "Fundamentals"]
//     },
//     {
//       title: "Vue",
//       description: "Some description about the vue",
//       topics: ["Introduction", "Javascript", "Why Vue?", "Vue Bindings", "Component Interaction"]
//     }
//   ]);
//   const [addedTopic, setAddedTopic] = useState([]);
//   const [showAgendaBlock, setShowAgendaBlock] = useState(false);

//   const handleNewTitle=(e)=>{
//     setNewTitle(e.target.value);
//   }

//   const handleNewDescription=(e)=>{
//     setNewDescription(e.target.value);
//   }

//   const handleNewTopic=(e)=>{
//     setNewTopic(e.target.value);
//   }

//   const addTopic = () => {
//     setAddedTopic([...addedTopic, newTopic]);
//     setNewTopic("");
//   };

//   const addAgenda = () => {
//     const agenda = {
//       title: newTitle,
//       description: newDescription,
//       topics: addedTopic,
//     };

//     setAddedAgenda([...addedAgenda, agenda]);
//     setNewTitle("");
//     setNewDescription("");
//     setNewTopic("");
//     setAddedTopic([]);
//   };

//   const preventSubmit = (e) => {
//     e.preventDefault();
//   };

//   const showAgendaBlockfn = () => {
//     setShowAgendaBlock(!showAgendaBlock);
//   };

//   return (
//     <div>
//       <h1 className="mx-5 mb-5">Agenda Manager</h1>
//       {!showAgendaBlock && (
//         <div className="container" role="addAgenda">
//           <button className="btn btn-info" role="goToView" onClick={showAgendaBlockfn}>
//             Click To View
//           </button>
//           <form onSubmit={preventSubmit}>
//             <div className="my-3">
//               <label className="form-label">Title</label>
//               <input
//                 type="text"
//                 name="newTitle"
//                 placeholder="Enter the title"
//                 className="form-control"
//                 role="inputTitle"
//                 value={newTitle}
//                 onChange={handleNewTitle}
//               />
//               <small className="text-danger" data-testid="invalidTitle">
//                 {newTitle.trim().length === 0 ? "Title is required" : ""}
//               </small>
//             </div>
//             <div className="my-3">
//               <label className="form-label">Description</label>
//               <input
//                 type="text"
//                 name="newDescription"
//                 placeholder="Enter the description"
//                 className="form-control"
//                 role="inputDescription"
//                 value={newDescription}
//                 onChange={handleNewDescription}
//               />
//               <small className="text-danger" data-testid="invalidDescription">
//                 {newDescription.trim().length === 0 ? "Description is required" : ""}
//               </small>
//             </div>
//             <div className="my-3 w-50">
//               <label className="form-label">Enter topic</label>
//               <input
//                 type="text"
//                 name="newTopic"
//                 placeholder="Enter the topic"
//                 className="form-control"
//                 role="inputTopic"
//                 value={newTopic}
//                 onChange={handleNewTopic}
//               />
//               <small className="text-danger" data-testid="invalidTopic">
//                 {newTopic.trim().length === 0 && addedTopic.length === 0 ? "Topic is required" : ""}
//               </small>
//             </div>
//             <button className="btn btn-success addAlign" role="addTopicBtn" onClick={addTopic} disabled={newTopic.trim().length === 0}>
//               + Add Topic
//             </button>
//             <button
//               className="btn btn-success submitAlign"
//               role="submitAgendaBtn"
//               onClick={addAgenda}
//               disabled={newTitle.trim().length === 0 || newDescription.trim().length === 0 || addedTopic.length === 0}
//             >
//               Submit Agenda
//             </button>
//           </form>
//           {addedTopic.length === 0 &&
//             <div className="text-danger ml-2 mt-5" data-testid="noTopicsMsg">
//               No Topics Added
//             </div>
//           }
//           {addedTopic.length !== 0 &&
//             <div className="card my-3">
//               <div className="card-header">Added Topics</div>
//               <div className="card-body">
//                 <ul className="list-group">
//                   {addedTopic.map((topic, index) => (
//                     <li key={index} className="list-group-item" role="topicList">
//                       {topic}
//                     </li>
//                   ))}
//                 </ul>
//               </div>
//               <div className="card-footer">Refer the topics you added</div>
//             </div>
//           }
//         </div>
//       )}
//       {showAgendaBlock && (
//         <div className="container" role="viewAgenda">
//           <button className="btn btn-info" role="goToAdd" onClick={showAgendaBlockfn}>
//             Click To Add Agenda
//           </button>
//           {addedAgenda.map((agenda, index) => (
//             <div key={index} className="card my-3" role="cards">
//               <div className="card-header">{agenda.title}</div>
//               <div className="card-body">
//                 <ul className="list-group">
//                   {agenda.topics.map((topic, idx) => (
//                     <li key={idx} className="list-group-item">
//                       {topic}
//                     </li>
//                   ))}
//                 </ul>
//               </div>
//               <div className="card-footer">{agenda.description}</div>
//             </div>
//           ))}
//         </div>
//       )}
//     </div>
//   );
// }

// export default App;