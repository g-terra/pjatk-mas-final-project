/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
}

module.exports = {
  env: {
    deviceManufactureApi: 'http://localhost:8080',
  },
  nextConfig
}

