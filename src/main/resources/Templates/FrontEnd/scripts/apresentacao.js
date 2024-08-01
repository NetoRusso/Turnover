// const boxes = document.querySelectorAll(".box");
// let activeIndex = 0;
// let isTransitioning = false;

// function updateCurrentImg() {
//   isTransitioning = true;

//   boxes.forEach((box, index) => {
//     const isActive = index === activeIndex;
//     box.classList.toggle("expanded", isActive);
//     box.classList.toggle("closed", !isActive);
//   });

//   setTimeout(() => {
//     isTransitioning = false;
//   }, 500);
// }

// function handleArrowKey(event) {
//   if (isTransitioning) {
//     return;
//   }

//   if (event.key === "ArrowRight") {
//     activeIndex = (activeIndex + 1) % boxes.length;
//   } else if (event.key === "ArrowLeft") {
//     activeIndex = (activeIndex - 1 + boxes.length) % boxes.length;
//   }

//   updateCurrentImg();
// }

// function handleBoxClick(index) {
//   if (isTransitioning) {
//     return;
//   }

//   if (index === activeIndex && boxes[index].classList.contains("expanded")) {
//     boxes.forEach((box) => box.classList.remove("closed", "expanded"));
//     activeIndex = 0;
//   } else {
//     activeIndex = index;
//     updateCurrentImg();
//   }
// }

// document.addEventListener("keydown", handleArrowKey);

// updateCurrentImg();

// boxes.forEach((box, index) => {
//   box.addEventListener("click", () => handleBoxClick(index));
// });

const boxes = document.querySelectorAll(".box");
let activeIndex = 0;
let isTransitioning = false;

function updateCurrentImg() {
  isTransitioning = true;

  boxes.forEach((box, index) => {
    const isActive = index === activeIndex;
    box.classList.toggle("expanded", isActive);
    box.classList.toggle("closed", !isActive);
  });

  setTimeout(() => {
    isTransitioning = false;
  }, 500);
}

function handleArrowKey(event) {
  if (isTransitioning) {
    return;
  }

  if (event.key === "ArrowRight") {
    activeIndex = (activeIndex + 1) % boxes.length;
  } else if (event.key === "ArrowLeft") {
    activeIndex = (activeIndex - 1 + boxes.length) % boxes.length;
  }

  updateCurrentImg();
}

function handleBoxClick(index) {
  if (isTransitioning) {
    return;
  }

  if (index === activeIndex && boxes[index].classList.contains("expanded")) {
    boxes.forEach((box) => box.classList.remove("closed", "expanded"));
    activeIndex = 0;
  } else {
    activeIndex = index;
    updateCurrentImg();
  }
}

document.addEventListener("keydown", handleArrowKey);

updateCurrentImg();

boxes.forEach((box, index) => {
  box.addEventListener("click", () => handleBoxClick(index));
});