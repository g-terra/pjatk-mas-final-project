FROM node:lts as dependencies
WORKDIR /device-manufacture-ui
COPY package.json yarn.lock ./
RUN yarn install --frozen-lockfile

FROM node:lts as builder
WORKDIR /device-manufacture-ui
COPY . .
COPY --from=dependencies /device-manufacture-ui/node_modules ./node_modules
RUN yarn build

FROM node:lts as runner
WORKDIR /device-manufacture-ui
ENV NODE_ENV production

COPY --from=builder /device-manufacture-ui/next.config.js ./
COPY --from=builder /device-manufacture-ui/public ./public
COPY --from=builder /device-manufacture-ui/.next ./.next
COPY --from=builder /device-manufacture-ui/node_modules ./node_modules
COPY --from=builder /device-manufacture-ui/package.json ./package.json

EXPOSE 3000
CMD ["yarn", "start"]